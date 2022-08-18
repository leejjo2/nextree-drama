import { CommandResponse, NameValueList } from '@nara/accent';
import { ApiClient } from '@nara/prologue';
import {
  AddPersonalPageCommand,
  CopyPersonalPageFromTeamBookCommand,
  MakeBasePersonalPageCommand,
  ModifyPersonalBookCommand,
  ModifyPersonalPageCommand,
  RegisterPersonalBookCommand,
  RemovePersonalBookCommand,
  RemovePersonalPageCommand,
} from '../command';
import { PersonalBookCdo, PersonalPageCdo } from '../../api-model';

class PersonalBookFlowApiStub {
  private static _instance: PersonalBookFlowApiStub;

  private readonly client = new ApiClient('/api/address-book/feature/personalbook');

  static get instance() {
    if (!PersonalBookFlowApiStub._instance) {
      PersonalBookFlowApiStub._instance = new PersonalBookFlowApiStub();
    }
    return PersonalBookFlowApiStub._instance;
  }

  async registerPersonalBook(personalBookCdo: PersonalBookCdo): Promise<CommandResponse> {
    const command = RegisterPersonalBookCommand.new(
      personalBookCdo,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/register-personal-book/command', command);
  }

  async modifyPersonalBook(personalBookId: string, nameValueList: NameValueList): Promise<CommandResponse> {
    const command = ModifyPersonalBookCommand.new(
      personalBookId,
      nameValueList,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/modify-personal-book/command', command);
  }

  async addPersonalPage(personalPageCdo: PersonalPageCdo): Promise<CommandResponse> {
    const command = AddPersonalPageCommand.new(
      personalPageCdo,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/add-personal-page/command', command);
  }

  async modifyPersonalPage(personalPageId: string, nameValueList: NameValueList): Promise<CommandResponse> {
    const command = ModifyPersonalPageCommand.new(
      personalPageId,
      nameValueList,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/modify-personal-page/command', command);
  }

  async makeBasePersonalPage(personalBookId: string, personalPageId: string): Promise<CommandResponse> {
    const command = MakeBasePersonalPageCommand.new(
      personalBookId,
      personalPageId,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/make-base-personal-page/command', command);
  }

  async copyPersonalPageFromTeamBook(personalPageId: string, teamBookId: string): Promise<CommandResponse> {
    const command = CopyPersonalPageFromTeamBookCommand.new(
      personalPageId,
      teamBookId,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/copy-personal-page-from-team-book/command', command);
  }

  async removePersonalBook(personalBookId: string): Promise<CommandResponse> {
    const command = RemovePersonalBookCommand.new(
      personalBookId,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/remove-personal-book/command', command);
  }

  async removePersonalPage(personalPageId: string): Promise<CommandResponse> {
    const command = RemovePersonalPageCommand.new(
      personalPageId,
    );
    return this.client.postNotNull<CommandResponse>(CommandResponse, '/remove-personal-page/command', command);
  }
}

export default PersonalBookFlowApiStub;

