import { CommandResponse, NameValueList } from '@nara/accent';
import { ApiClient } from '@nara/prologue';
import {
  AddTeamPageCommand,
  CopyTeamPageFromPersonalBookCommand,
  MakeBaseTeamPageCommand,
  ModifyTeamAddressPageCommand,
  ModifyTeamBookCommand,
  RegisterTeamBookCommand,
  RemoveTeamBookCommand,
} from '../command';
import { TeamBookCdo, TeamPageCdo } from '../../api-model';
import { RemovePersonalPageCommand } from '../../../personalbook';

class TeamBookFlowApiStub {
  static _instance: TeamBookFlowApiStub;

  private readonly client = new ApiClient('/api/address-book/feature/teambook');

  static get instance() {
    if (!TeamBookFlowApiStub._instance) {
      TeamBookFlowApiStub._instance = new TeamBookFlowApiStub();
    }
    return TeamBookFlowApiStub._instance;
  }

  async registerTeamBook(teamBookCdo: TeamBookCdo): Promise<CommandResponse> {
    const command = RegisterTeamBookCommand.new(
      teamBookCdo,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/register-team-book/command', command);
  }

  async modifyTeamBook(teamBookId: string, nameValueList: NameValueList): Promise<CommandResponse> {
    const command = ModifyTeamBookCommand.new(
      teamBookId,
      nameValueList,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/modify-team-book/command', command);
  }

  async addTeamPage(teamPageCdo: TeamPageCdo): Promise<CommandResponse> {
    const command = AddTeamPageCommand.new(
      teamPageCdo,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/add-team-page/command', command);
  }

  async modifyTeamPage(teamPageId: string, nameValueList: NameValueList): Promise<CommandResponse> {
    const command = ModifyTeamAddressPageCommand.new(
      teamPageId,
      nameValueList,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/modify-team-page/command', command);
  }

  async makeBaseTeamPage(teamBookId: string, teamPageId: string): Promise<CommandResponse> {
    const command = MakeBaseTeamPageCommand.new(
      teamBookId,
      teamPageId,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/make-base-team-page/command', command);
  }

  async copyTeamPageFromPersonalBook(teamPageId: string, personalBookId: string): Promise<CommandResponse> {
    const command = CopyTeamPageFromPersonalBookCommand.new(
      teamPageId,
      personalBookId,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/copy-team-page-from-personal-book/command', command);
  }

  async removeTeamBook(teamBookId: string): Promise<CommandResponse> {
    const command = RemoveTeamBookCommand.new(
      teamBookId,
    );
    return this.client.postNotNull(CommandResponse, '/remove-team-book/command', command);
  }

  async removeTeamPage(teamPageId: string): Promise<CommandResponse> {
    const command = RemovePersonalPageCommand.new(
      teamPageId,
    );
    return this.client.postNotNull(CommandResponse, '/remove-team-page/command', command);
  }
}

export default TeamBookFlowApiStub;

