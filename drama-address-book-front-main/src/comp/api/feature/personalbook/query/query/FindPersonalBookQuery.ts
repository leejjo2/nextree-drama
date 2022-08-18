import { QueryRequest } from '@nara/accent';
import { AddressBook } from '../../../../aggregate';

class FindPersonalBookQuery extends QueryRequest<AddressBook> {
  personalBookId: string;

  constructor(personalBookId: string) {
    super(AddressBook);
    this.personalBookId = personalBookId;
  }

  static fromDomain(domain: FindPersonalBookQuery): FindPersonalBookQuery {
    const query = new FindPersonalBookQuery(
      domain.personalBookId,
    );

    query.setResponse(domain);
    return query;
  }

  static byQuery(personalBookId: string) {
    const query = new FindPersonalBookQuery(
      personalBookId,
    );

    return query;
  }

}

export default FindPersonalBookQuery;

