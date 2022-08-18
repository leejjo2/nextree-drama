import { QueryRequest } from '@nara/accent';
import { AddressPage } from '../../../../aggregate';

class FindPersonalPageQuery extends QueryRequest<AddressPage> {
  personalPageId: string;

  constructor(personalPageId: string) {
    super(AddressPage);
    this.personalPageId = personalPageId;
  }

  static fromDomain(domain: FindPersonalPageQuery): FindPersonalPageQuery {
    const query = new FindPersonalPageQuery(
      domain.personalPageId,
    );

    query.setResponse(domain);
    return query;
  }

  static byQuery(personalPageId: string) {
    const query = new FindPersonalPageQuery(
      personalPageId,
    );

    return query;
  }

}

export default FindPersonalPageQuery;

