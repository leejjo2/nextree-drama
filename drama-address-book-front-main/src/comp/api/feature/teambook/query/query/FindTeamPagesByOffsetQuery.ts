import { QueryRequest } from '@nara/accent';
import { AddressPage } from '../../../../aggregate';

class FindTeamPagesByOffsetQuery extends QueryRequest<AddressPage> {
  teamBookId: string;

  constructor(teamBookId: string) {
    super(AddressPage);
    this.teamBookId = teamBookId;
  }

  static fromDomain(domain: FindTeamPagesByOffsetQuery): FindTeamPagesByOffsetQuery {
    const query = new FindTeamPagesByOffsetQuery(
      domain.teamBookId,
    );

    query.setResponse(domain);
    return query;
  }

  static byQuery(teamBookId: string) {
    const query = new FindTeamPagesByOffsetQuery(
      teamBookId,
    );

    return query;
  }

}

export default FindTeamPagesByOffsetQuery;

