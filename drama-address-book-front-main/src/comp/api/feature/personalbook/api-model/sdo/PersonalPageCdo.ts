import { Address, Field } from '../../../../aggregate';

class PersonalPageCdo {
  name: string;
  address: Address;
  phoneNumber: string;
  fields: Field[];
  personalBookId: string;

  constructor(name: string, address: Address, phoneNumber: string, fields: Field[], personalBookId: string) {
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.fields = fields;
    this.personalBookId = personalBookId;
  }

  static new(): PersonalPageCdo {
    return new PersonalPageCdo('', Address.new(), '', [], '');
  }

}

export default PersonalPageCdo;

