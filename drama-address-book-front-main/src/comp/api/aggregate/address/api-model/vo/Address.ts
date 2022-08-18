class Address {
  zipCode: string;
  zipAddress: string;
  city: string;
  state: string;
  street: string;
  country: string;

  constructor(zipCode: string, zipAddress: string, city: string, state: string, street: string, country: string) {
    this.zipCode = zipCode;
    this.zipAddress = zipAddress;
    this.city = city;
    this.state = state;
    this.street = street;
    this.country = country;
  }

  static fromDomains(domains: Address[]): Address[] {
    return domains.map(domain => this.fromDomain(domain));
  }

  static fromDomain(domain: Address): Address {
    const address = new Address(
      domain.zipCode,
      domain.zipAddress,
      domain.city,
      domain.state,
      domain.street,
      domain.country,
    );

    return address;
  }

  static new(): Address {
    return new Address('', '', '', '', '', '');
  }

  get fullAddress(): string {
    return `${this.zipCode && `(${this.zipCode})`} ${this.city} ${this.state} ${this.street} ${this.zipAddress}`;
  }

}

export default Address;
