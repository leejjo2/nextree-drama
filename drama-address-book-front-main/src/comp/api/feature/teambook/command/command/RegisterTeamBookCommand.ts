import { CommandRequest, CommandType } from '@nara/accent';
import { TeamBookCdo } from '../../api-model';

class RegisterTeamBookCommand extends CommandRequest {
  teamBookCdo: TeamBookCdo;

  constructor(teamBookCdo: TeamBookCdo) {
    super(CommandType.UserDefined);
    this.teamBookCdo = teamBookCdo;
  }

  static new(teamBookCdo: TeamBookCdo) {
    const command = new RegisterTeamBookCommand(
      teamBookCdo,
    );

    return command;
  }

}

export default RegisterTeamBookCommand;

