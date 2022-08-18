import { QueryRequest } from '@nara/accent';
import { AddressPage } from '../../../../aggregate';

class FindPersonalPagesQuery extends QueryRequest<AddressPage> {
  personalBookId: string;

  constructor(personalBookId: string) {
    super(AddressPage);
    this.personalBookId = personalBookId;
  }

  static fromDomain(domain: FindPersonalPagesQuery): FindPersonalPagesQuery {
    const query = new FindPersonalPagesQuery(
      domain.personalBookId,
    );

    query.setResponse(domain);
    return query;
  }

  static byQuery(personalBookId: string) {
    const query = new FindPersonalPagesQuery(
      personalBookId,
    );

    return query;
  }

}

export default FindPersonalPagesQuery;

