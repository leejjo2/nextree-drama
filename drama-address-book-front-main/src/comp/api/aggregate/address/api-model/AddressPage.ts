import { NameValueList, StageEntity } from '@nara/accent';
import { Address, Field, GeoCoordinate } from './vo';

class AddressPage extends StageEntity {
  geoCoordinate: GeoCoordinate | null = null;
  name: string;
  address: Address | null = null;
  phoneNumber: string;
  fields: Field[] = [];
  baseAddress: boolean;
  memo: string;
  addressBookId: string;

  constructor(name: string, phoneNumber: string, baseAddress: boolean, memo: string, addressBookId: string) {
    super();
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.baseAddress = baseAddress;
    this.memo = memo;
    this.addressBookId = addressBookId;
  }

  static fromDomain(domain: AddressPage): AddressPage {
    const addressPage = new AddressPage(
      domain.name,
      domain.phoneNumber,
      domain.baseAddress,
      domain.memo,
      domain.addressBookId,
    );

    addressPage.setStageEntity(domain);
    addressPage.geoCoordinate = domain.geoCoordinate ? GeoCoordinate.fromDomain(domain.geoCoordinate) : null;
    addressPage.address = domain.address ? Address.fromDomain(domain.address) : null;
    addressPage.fields = domain.fields ? Field.fromDomains(domain.fields) : [];
    return addressPage;
  }

  static fromDomains(domains: AddressPage[]): AddressPage[] {
    return domains.map(domain => this.fromDomain(domain));
  }

  static asNameValues(model: AddressPage): NameValueList {
    return NameValueList.fromModel(AddressPage, model, {
      geoCoordinate: 'Json',
      name: String,
      address: 'Json',
      phoneNumber: String,
      fields: 'Json',
      baseAddress: String,
      memo: String,
      addressBookId: String,

    });
  }

  static new(): AddressPage {
    return new AddressPage('', '', false, '', '');
  }

}

export default AddressPage;

