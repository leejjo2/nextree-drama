import {
  Address,
  AddressPage,
  AddTeamPageCommand,
  Field,
  FindTeamPageQuery,
  TeamBookFlowApiStub,
  TeamBookSeekApiStub,
  TeamPageCdo,
} from '../../../api';
import { makeAutoObservable, runInAction } from 'mobx';
import { CommandResponse, NameValueList, NotInstantiatedException } from '@nara/accent';
import _ from 'lodash';

class TeamPageStateKeeper {
  private static _instance: TeamPageStateKeeper;

  private readonly teamBookFlowApi: TeamBookFlowApiStub;
  private readonly teamBookSeekApi: TeamBookSeekApiStub;

  teamPage: AddressPage | null = null;

  get fullAddress(): string {
    const address = this.teamPage?.address;
    return !address ? '' : `${address?.zipCode && `(${address?.zipCode})`} ${address?.city || ''} ${address?.state || ''} ${address?.street || ''} ${address?.zipAddress || ''}`;
  }

  static get instance() {
    if (!TeamPageStateKeeper._instance) {
      TeamPageStateKeeper._instance = new TeamPageStateKeeper();
    }
    return TeamPageStateKeeper._instance;
  }

  constructor(
    teamBookFlowApi: TeamBookFlowApiStub = TeamBookFlowApiStub.instance,
    teamBookSeekApi: TeamBookSeekApiStub = TeamBookSeekApiStub.instance,
  ) {
    this.teamBookFlowApi = teamBookFlowApi;
    this.teamBookSeekApi = teamBookSeekApi;
    makeAutoObservable(this, {}, { autoBind: true });
  }

  init(teamBookId: string) {
    this.teamPage = Object.assign(AddressPage.new(), { addressBookId: teamBookId });
  }

  setTeamPageProp(name: string, value: any) {
    if (!this.teamPage) {
      throw new NotInstantiatedException('TeamPageStateKeeper.setTeamPageProp', 'teamPage is null');
    }
    this.teamPage = Object.assign(AddressPage.new(), _.set(this.teamPage, name, value));
  }

  setTeamPageField(targetIndex: number, name: string, value: any) {
    if (!this.teamPage) {
      throw new NotInstantiatedException('TeamPageStateKeeper.setTeamPageField', 'teamPage is null');
    }

    const fields = this.teamPage.fields.map((field, index) =>
      targetIndex === index
        ? Object.assign(Field.new(), _.set(field, name, value))
        : field,
    );

    this.setTeamPageProp('fields', fields);
  }

  addField() {
    if (!this.teamPage) {
      throw new NotInstantiatedException('TeamPageStateKeeper.addField', 'teamPage is null');
    }
    this.setTeamPageProp('fields', [...this.teamPage.fields, Field.new()]);
  }

  removeField(targetIndex: number) {
    if (!this.teamPage) {
      throw new NotInstantiatedException('TeamPageStateKeeper.removeField', 'teamPage is null');
    }
    this.setTeamPageProp('fields', this.teamPage.fields.filter((field, index) => targetIndex !== index));
  }

  async add(teamPage: AddressPage): Promise<CommandResponse> {
    const teamPageCdo = TeamPageCdo.new();
    teamPageCdo.teamBookId = teamPage.addressBookId;
    teamPageCdo.name = teamPage.name;
    teamPageCdo.phoneNumber = teamPage.phoneNumber;
    teamPageCdo.address = teamPage.address || Address.new();
    teamPageCdo.fields = teamPage.fields;

    const command = AddTeamPageCommand.new(teamPageCdo);

    return this.teamBookFlowApi.addTeamPage(teamPageCdo);
  }

  async modify(teamPageId: string, nameValues: NameValueList): Promise<CommandResponse> {
    return this.teamBookFlowApi.modifyTeamPage(teamPageId, nameValues);
  }

  async findTeamPageById(teamPageId: string): Promise<AddressPage> {
    const teamPageQuery = FindTeamPageQuery.byQuery(teamPageId);
    const teamPage = await this.teamBookSeekApi.findTeamPage(teamPageQuery);

    runInAction(() => this.teamPage = teamPage);
    return teamPage;
  }

  async makeBaseTeamPage(teamPage: AddressPage): Promise<CommandResponse> {
    return this.teamBookFlowApi.makeBaseTeamPage(teamPage.addressBookId, teamPage.id);
  }

  async removeTeamPage(teamPageId: string): Promise<CommandResponse> {
    return this.teamBookFlowApi.removeTeamPage(teamPageId);
  }
}

export default TeamPageStateKeeper;
