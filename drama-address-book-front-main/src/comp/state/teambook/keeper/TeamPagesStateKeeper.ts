import { AddressPage, FindTeamPagesByOffsetQuery, TeamBookSeekApiStub } from '../../../api';
import { makeAutoObservable, runInAction } from 'mobx';
import { Offset } from '@nara/accent';
import { set } from 'lodash';

class TeamPagesStateKeeper {
  private static _instance: TeamPagesStateKeeper;

  private readonly teamBookSeekApi: TeamBookSeekApiStub;

  teamPages: AddressPage[] = [];
  offset: Offset = Offset.newDescending(0, 12, 'registrationTime');
  totalCount = 0;
  teamBookId = '';

  static get instance() {
    if (!TeamPagesStateKeeper._instance) {
      TeamPagesStateKeeper._instance = new TeamPagesStateKeeper();
    }
    return TeamPagesStateKeeper._instance;
  }

  constructor(
    teamBookSeekApi: TeamBookSeekApiStub = TeamBookSeekApiStub.instance,
  ) {
    this.teamBookSeekApi = teamBookSeekApi;
    makeAutoObservable(this, {}, { autoBind: true });
  }

  setOffsetProp(name: string, value: any) {
    this.offset = Object.assign(new Offset(), set(this.offset, name, value));
  }

  setTeamBookId(teamBookId: string) {
    this.teamBookId = teamBookId;
  }

  changePage(page: number) {
    const { limit } = this.offset;
    this.offset = Object.assign(new Offset(), set(this.offset, 'offset', limit * (page - 1)));
  }

  async findTeamPages(teamBookId: string): Promise<AddressPage[]> {
    const query = FindTeamPagesByOffsetQuery.byQuery(teamBookId);
    query.offset = this.offset;
    const offsetElementList = await this.teamBookSeekApi.findTeamPagesByOffset(query);

    runInAction(() => {
      this.teamPages = offsetElementList.results;
      this.totalCount = offsetElementList.totalCount;
    });

    return this.teamPages;
  }
}

export default TeamPagesStateKeeper;
