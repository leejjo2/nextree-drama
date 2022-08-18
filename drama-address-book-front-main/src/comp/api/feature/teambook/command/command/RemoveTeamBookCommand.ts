import { CommandRequest, CommandType } from '@nara/accent';

class RemoveTeamBookCommand extends CommandRequest {
  teamBookId: string;

  constructor(teamBookId: string) {
    super(CommandType.UserDefined);
    this.teamBookId = teamBookId;
  }

  static new(teamBookId: string) {
    const command = new RemoveTeamBookCommand(
      teamBookId,
    );

    return command;
  }

}

export default RemoveTeamBookCommand;

