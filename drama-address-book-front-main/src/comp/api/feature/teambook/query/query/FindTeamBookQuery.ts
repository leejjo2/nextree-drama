import { QueryRequest } from '@nara/accent';
import { AddressBook } from '../../../../aggregate';

class FindTeamBookQuery extends QueryRequest<AddressBook> {
  teamBookId: string;

  constructor(teamBookId: string) {
    super(AddressBook);
    this.teamBookId = teamBookId;
  }

  static fromDomain(domain: FindTeamBookQuery): FindTeamBookQuery {
    const query = new FindTeamBookQuery(
      domain.teamBookId,
    );

    query.setResponse(domain);
    return query;
  }

  static byQuery(teamBookId: string) {
    const query = new FindTeamBookQuery(
      teamBookId,
    );

    return query;
  }

}

export default FindTeamBookQuery;

