import { QueryRequest } from '@nara/accent';
import { AddressPage } from '../../../../aggregate';

class FindTeamPagesQuery extends QueryRequest<AddressPage> {
  teamBookId: string;

  constructor(teamBookId: string) {
    super(AddressPage);
    this.teamBookId = teamBookId;
  }

  static fromDomain(domain: FindTeamPagesQuery): FindTeamPagesQuery {
    const query = new FindTeamPagesQuery(
      domain.teamBookId,
    );

    query.setResponse(domain);
    return query;
  }

  static byQuery(teamBookId: string) {
    const query = new FindTeamPagesQuery(
      teamBookId,
    );

    return query;
  }

}

export default FindTeamPagesQuery;

