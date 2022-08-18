class PersonalBookCdo {
  name: string;
  description: string;
  citizenId: string;

  constructor(name: string, description: string, citizenId: string) {
    this.name = name;
    this.description = description;
    this.citizenId = citizenId;
  }

  static new(): PersonalBookCdo {
    return new PersonalBookCdo('', '', '');
  }

}

export default PersonalBookCdo;

