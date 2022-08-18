import { Address, Field } from '~/comp';

class TeamPageCdo {
  name: string;

  address: Address;

  phoneNumber: string;

  fields: Field[];

  teamBookId: string;

  constructor(name: string, address: Address, phoneNumber: string, fields: Field[], teamBookId: string) {
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.fields = fields;
    this.teamBookId = teamBookId;
  }

  static new(): TeamPageCdo {
    return new TeamPageCdo('', Address.new(), '', [], '');
  }

}

export default TeamPageCdo;

