import { CommandResponse, NameValueList, NotInstantiatedException } from '@nara/accent';
import { makeAutoObservable, runInAction } from 'mobx';
import { set } from 'lodash';
import {
  Address,
  AddressPage,
  Field,
  FindPersonalPageQuery,
  PersonalBookFlowApiStub,
  PersonalBookSeekApiStub,
  PersonalPageCdo,
} from '../../../api';

class PersonalPageStateKeeper {
  private static _instance: PersonalPageStateKeeper;

  private readonly personalBookFlowApi: PersonalBookFlowApiStub;
  private readonly personalBookSeekApi: PersonalBookSeekApiStub;

  personalPage: AddressPage | null = null;

  get fullAddress(): string {
    const address = this.personalPage?.address;
    return !address ? '' : `${address?.zipCode && `(${address?.zipCode})`} ${address?.city || ''} ${address?.state || ''} ${address?.street || ''} ${address?.zipAddress || ''}`;
  }

  static get instance() {
    if (!PersonalPageStateKeeper._instance) {
      PersonalPageStateKeeper._instance = new PersonalPageStateKeeper();
    }
    return PersonalPageStateKeeper._instance;
  }

  constructor(
    personalBookFlowApi: PersonalBookFlowApiStub = PersonalBookFlowApiStub.instance,
    personalBookSeekApi: PersonalBookSeekApiStub = PersonalBookSeekApiStub.instance,
  ) {
    this.personalBookFlowApi = personalBookFlowApi;
    this.personalBookSeekApi = personalBookSeekApi;
    makeAutoObservable(this, {}, { autoBind: true });
  }

  init(personalBookId: string) {
    this.personalPage = Object.assign(AddressPage.new(), { addressBookId: personalBookId });
  }

  setPersonalPageProp(name: string, value: any) {
    if (!this.personalPage) {
      throw new NotInstantiatedException('PersonalPageStateKeeper.setPersonalPageProp', 'personalPage is null');
    }
    this.personalPage = Object.assign(AddressPage.new(), set(this.personalPage, name, value));
  }

  setPersonalPageField(targetIndex: number, name: string, value: any) {
    if (!this.personalPage) {
      throw new NotInstantiatedException('PersonalPageStateKeeper.setPersonalPageField', 'personalPage is null');
    }

    const fields = this.personalPage.fields.map((field, index) =>
      targetIndex === index
        ? Object.assign(Field.new(), set(field, name, value))
        : field,
    );

    this.setPersonalPageProp('fields', fields);
  }

  addField() {
    if (!this.personalPage) {
      throw new NotInstantiatedException('PersonalPageStateKeeper.addField', 'addrespersonalPagesPage is null');
    }
    this.setPersonalPageProp('fields', [...this.personalPage.fields, Field.new()]);
  }

  removeField(targetIndex: number) {
    if (!this.personalPage) {
      throw new NotInstantiatedException('PersonalPageStateKeeper.removeField', 'personalPage is null');
    }
    this.setPersonalPageProp('fields', this.personalPage.fields.filter((field, index) => targetIndex !== index));
  }

  clear() {
    this.personalPage = null;
  }

  async add(personalPage: AddressPage): Promise<CommandResponse> {
    const personalPageCdo = PersonalPageCdo.new();
    personalPageCdo.personalBookId = personalPage.addressBookId;
    personalPageCdo.name = personalPage.name;
    personalPageCdo.phoneNumber = personalPage.phoneNumber;
    personalPageCdo.address = personalPage.address || Address.new();
    personalPageCdo.fields = personalPage.fields;

    return this.personalBookFlowApi.addPersonalPage(personalPageCdo);
  }

  async modify(personalPageId: string, nameValues: NameValueList): Promise<CommandResponse> {
    return this.personalBookFlowApi.modifyPersonalPage(personalPageId, nameValues);
  }

  async findPersonalPageById(personalPageId: string): Promise<AddressPage> {
    const personalPageQuery = FindPersonalPageQuery.byQuery(personalPageId);
    const personalPage = await this.personalBookSeekApi.findPersonalPage(personalPageQuery);

    runInAction(() => this.personalPage = personalPage);
    return personalPage;
  }

  async makeBasePersonalPage(personalPage: AddressPage): Promise<CommandResponse> {
    return this.personalBookFlowApi.makeBasePersonalPage(personalPage.addressBookId, personalPage.id);
  }

  async copyPersonalPageFromTeamBook(personalPageId: string, teamBookId: string): Promise<CommandResponse> {
    return this.personalBookFlowApi.copyPersonalPageFromTeamBook(personalPageId, teamBookId);
  }

  async removePersonalPage(personalPageId: string): Promise<CommandResponse> {
    return this.personalBookFlowApi.removePersonalPage(personalPageId);
  }
}

export default PersonalPageStateKeeper;
