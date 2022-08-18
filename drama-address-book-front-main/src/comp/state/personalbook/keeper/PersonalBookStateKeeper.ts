import { CommandResponse, NameValueList, NotInstantiatedException } from '@nara/accent';
import { set } from 'lodash';
import { makeAutoObservable, observable, runInAction } from 'mobx';
import {
  AddressBook,
  FindPersonalBookQuery,
  PersonalBookCdo,
  PersonalBookFlowApiStub,
  PersonalBookSeekApiStub,
} from '../../../api';

class PersonalBookStateKeeper {
  private static _instance: PersonalBookStateKeeper;

  private readonly personalBookFlowApi: PersonalBookFlowApiStub;
  private readonly personalBookSeekApi: PersonalBookSeekApiStub;

  personalBook: AddressBook | null = null;

  static get instance() {
    if (!PersonalBookStateKeeper._instance) {
      PersonalBookStateKeeper._instance = new PersonalBookStateKeeper();
    }
    return PersonalBookStateKeeper._instance;
  }

  constructor(
    personalBookFlowApi: PersonalBookFlowApiStub = PersonalBookFlowApiStub.instance,
    personalBookSeekApi: PersonalBookSeekApiStub = PersonalBookSeekApiStub.instance,
  ) {
    this.personalBookFlowApi = personalBookFlowApi;
    this.personalBookSeekApi = personalBookSeekApi;
    makeAutoObservable(this, {}, { autoBind: true });
  }

  init() {
    this.personalBook = AddressBook.new();
  }

  setPersonalBookProp(name: string, value: any) {
    if (!this.personalBook) {
      throw new NotInstantiatedException('PersonalBookStateKeeper.setPersonalBookProp', 'personalBook is null');
    }
    this.personalBook = set(this.personalBook, name, value);
  }

  clear() {
    this.personalBook = null;
  }

  async register(personalBookCdo: PersonalBookCdo): Promise<CommandResponse> {
    return this.personalBookFlowApi.registerPersonalBook(personalBookCdo);
  }

  async modify(personalBookId: string, nameValues: NameValueList): Promise<CommandResponse> {
    return this.personalBookFlowApi.modifyPersonalBook(personalBookId, nameValues);
  }

  async findPersonalAddressBookById(personalBookId: string): Promise<AddressBook> {
    const personalBookQuery = FindPersonalBookQuery.byQuery(personalBookId);
    const personalBook = await this.personalBookSeekApi.findPersonalBook(personalBookQuery);

    runInAction(() => this.personalBook = personalBook);
    return personalBook;
  }
}

export default PersonalBookStateKeeper;
