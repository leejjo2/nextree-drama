class TeamBookCdo {
  name: string;

  description: string;

  cineroomId: string;

  constructor(name: string, description: string, cineroomId: string) {
    this.name = name;
    this.description = description;
    this.cineroomId = cineroomId;
  }

  static new(): TeamBookCdo {
    return new TeamBookCdo('', '', '');
  }

}

export default TeamBookCdo;

