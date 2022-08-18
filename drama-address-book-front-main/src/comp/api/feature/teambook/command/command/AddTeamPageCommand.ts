import { CommandRequest, CommandType } from '@nara/accent';
import { TeamPageCdo } from '../../api-model';

class AddTeamPageCommand extends CommandRequest {
  teamPageCdo: TeamPageCdo;

  constructor(teamPageCdo: TeamPageCdo) {
    super(CommandType.UserDefined);
    this.teamPageCdo = teamPageCdo;
  }

  static new(teamPageCdo: TeamPageCdo) {
    const command = new AddTeamPageCommand(
      teamPageCdo,
    );

    return command;
  }

}

export default AddTeamPageCommand;

