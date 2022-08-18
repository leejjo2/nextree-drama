import { ApiClient } from '@nara/prologue';
import { FindTeamBookQuery, FindTeamPageQuery, FindTeamPagesByOffsetQuery, FindTeamPagesQuery } from '../query';
import { AddressBook, AddressPage } from '../../../../aggregate';
import { OffsetElementList } from '@nara/accent';

class TeamBookSeekApiStub {
  static _instance: TeamBookSeekApiStub;

  private readonly client = new ApiClient('/api/address-book/feature/teambook', {
    resDataName: 'queryResult',
  });

  static get instance() {
    if (!TeamBookSeekApiStub._instance) {
      TeamBookSeekApiStub._instance = new TeamBookSeekApiStub();
    }
    return TeamBookSeekApiStub._instance;
  }

  async findTeamBook(query: FindTeamBookQuery): Promise<AddressBook> {
    return this.client.postNotNull<AddressBook>(
      AddressBook,
      '/find-team-book/query',
      query,
    );
  }

  async findTeamPages(query: FindTeamPagesQuery): Promise<AddressPage[]> {
    return this.client.postArray<AddressPage>(
      AddressPage,
      '/find-team-pages/query',
      query,
    );
  }

  async findTeamPagesByOffset(query: FindTeamPagesByOffsetQuery): Promise<OffsetElementList<AddressPage>> {
    return this.client.postOffsetElementList<AddressPage>(
      AddressPage,
      '/find-team-pages-by-offset/query',
      query,
    );
  }

  async findTeamPage(query: FindTeamPageQuery): Promise<AddressPage> {
    return this.client.postNotNull<AddressPage>(
      AddressPage,
      '/find-team-page/query',
      query,
    );
  }

}

export default TeamBookSeekApiStub;

