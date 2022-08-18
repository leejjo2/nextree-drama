import { QueryRequest } from '@nara/accent';
import { AddressPage } from '../../../../aggregate';

class FindPersonalPagesByOffsetQuery extends QueryRequest<AddressPage> {
  personalBookId: string;

  constructor(personalBookId: string) {
    super(AddressPage);
    this.personalBookId = personalBookId;
  }

  static fromDomain(domain: FindPersonalPagesByOffsetQuery): FindPersonalPagesByOffsetQuery {
    const query = new FindPersonalPagesByOffsetQuery(
      domain.personalBookId,
    );

    query.setResponse(domain);
    return query;
  }

  static byQuery(personalBookId: string) {
    const query = new FindPersonalPagesByOffsetQuery(
      personalBookId,
    );

    return query;
  }

}

export default FindPersonalPagesByOffsetQuery;

