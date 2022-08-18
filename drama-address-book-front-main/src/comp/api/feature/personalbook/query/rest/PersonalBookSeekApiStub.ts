import { ApiClient } from '@nara/prologue';
import {
  FindPersonalBookQuery,
  FindPersonalPageQuery,
  FindPersonalPagesByOffsetQuery,
  FindPersonalPagesQuery,
} from '../query';
import { AddressBook, AddressPage } from '../../../../aggregate';
import { OffsetElementList } from '@nara/accent';

class PersonalBookSeekApiStub {
  private static _instance: PersonalBookSeekApiStub;

  private readonly client = new ApiClient('/api/address-book/feature/personalbook', {
    resDataName: 'queryResult',
  });

  static get instance() {
    if (!PersonalBookSeekApiStub._instance) {
      PersonalBookSeekApiStub._instance = new PersonalBookSeekApiStub();
    }
    return PersonalBookSeekApiStub._instance;
  }

  async findPersonalBook(query: FindPersonalBookQuery): Promise<AddressBook> {
    return this.client.postNotNull<AddressBook>(
      AddressBook,
      '/find-personal-book/query',
      query,
    );
  }

  async findPersonalPages(query: FindPersonalPagesQuery): Promise<AddressPage[]> {
    return this.client.postArray<AddressPage>(
      AddressPage,
      '/find-personal-pages/query',
      query,
    );
  }

  async findPersonalPagesByOffset(query: FindPersonalPagesByOffsetQuery): Promise<OffsetElementList<AddressPage>> {
    return this.client.postOffsetElementList<AddressPage>(
      AddressPage,
      '/find-personal-pages-by-offset/query',
      query,
    );
  }

  async findPersonalPage(query: FindPersonalPageQuery): Promise<AddressPage> {
    return this.client.postNotNull<AddressPage>(
      AddressPage,
      '/find-personal-page/query',
      query,
    );
  }

}

export default PersonalBookSeekApiStub;

