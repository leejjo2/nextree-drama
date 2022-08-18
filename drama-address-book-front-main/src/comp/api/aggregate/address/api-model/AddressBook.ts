import { NameValueList, StageEntity } from '@nara/accent';

class AddressBook extends StageEntity {
  name: string;
  description: string;

  constructor(name: string, description: string) {
    super();
    this.name = name;
    this.description = description;
  }

  static fromDomain(domain: AddressBook): AddressBook {
    const addressBook = new AddressBook(
      domain.name,
      domain.description,
    );

    addressBook.setStageEntity(domain);
    return addressBook;
  }

  static fromDomains(domains: AddressBook[]): AddressBook[] {
    return domains.map(domain => this.fromDomain(domain));
  }

  static asNameValues(model: AddressBook): NameValueList {
    return NameValueList.fromModel(AddressBook, model, {
      name: String,
      description: String,

    });
  }

  static new(): AddressBook {
    return new AddressBook('', '');
  }

}

export default AddressBook;

