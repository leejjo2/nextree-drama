import { NotInstantiatedException, Offset } from '@nara/accent';
import { set } from 'lodash';
import { makeAutoObservable, runInAction } from 'mobx';
import { AddressPage, FindPersonalPagesByOffsetQuery, PersonalBookSeekApiStub } from '../../../api';

class PersonalPagesStateKeeper {
  private static _instance: PersonalPagesStateKeeper;

  private readonly personalBookSeekApi: PersonalBookSeekApiStub;

  personalPages: AddressPage[] = [];
  offset: Offset = Offset.newAscending(0, 12, 'registrationTime');
  totalCount = 0;
  personalBookId = '';

  static get instance() {
    if (!PersonalPagesStateKeeper._instance) {
      PersonalPagesStateKeeper._instance = new PersonalPagesStateKeeper();
    }
    return PersonalPagesStateKeeper._instance;
  }

  constructor(
    personalBookSeekApi: PersonalBookSeekApiStub = PersonalBookSeekApiStub.instance,
  ) {
    this.personalBookSeekApi = personalBookSeekApi;
    makeAutoObservable(this, {}, { autoBind: true });
  }

  setPersonalPageProp(index: number, name: string, value: any) {
    if (!this.personalPages || !this.personalPages[index]) {
      throw new NotInstantiatedException('PersonalPagesStateKeeper.setAddressPageProp', `addressPages[${index}] is null`);
    }
    this.personalPages[index] = set(this.personalPages[index], name, value);
  }

  setOffsetProp(name: string, value: any) {
    this.offset = Object.assign(new Offset(), set(this.offset, name, value));
  }

  setPersonalBookId(personalBookId: string) {
    this.personalBookId = personalBookId;
  }

  changePage(page: number) {
    const { limit } = this.offset;
    this.offset = Object.assign(new Offset(), set(this.offset, 'offset', limit * (page - 1)));
  }

  clear() {
    this.personalPages = [];
    this.offset = Offset.newAscending(0, 12, 'registrationTime');
    this.totalCount = 0;
  }

  async findPersonalPages(personalBookId: string): Promise<AddressPage[]> {
    const query = FindPersonalPagesByOffsetQuery.byQuery(personalBookId);
    query.offset = this.offset;

    const offsetElementList = await this.personalBookSeekApi.findPersonalPagesByOffset(query);

    runInAction(() => {
      this.personalPages = offsetElementList.results;
      this.totalCount = offsetElementList.totalCount;
    });

    return this.personalPages;
  }
}

export default PersonalPagesStateKeeper;
