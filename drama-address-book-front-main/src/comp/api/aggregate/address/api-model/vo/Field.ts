class Field {
  name: string;
  value: string;
  descriptions: string;

  constructor(name: string, value: string, descriptions: string) {
    this.name = name;
    this.value = value;
    this.descriptions = descriptions;
  }

  static fromDomains(domains: Field[]): Field[] {
    return domains.map(domain => this.fromDomain(domain));
  }

  static fromDomain(domain: Field): Field {
    const field = new Field(
      domain.name,
      domain.value,
      domain.descriptions,
    );

    return field;
  }

  static new(): Field {
    return new Field('', '', '');
  }

}

export default Field;

