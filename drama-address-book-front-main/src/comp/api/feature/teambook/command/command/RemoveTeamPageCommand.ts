import { CommandRequest, CommandType } from '@nara/accent';

class RemoveTeamPageCommand extends CommandRequest {
  teamPageId: string;

  constructor(teamPageId: string) {
    super(CommandType.UserDefined);
    this.teamPageId = teamPageId;
  }

  static new(teamPageId: string) {
    const command = new RemoveTeamPageCommand(
      teamPageId,
    );

    return command;
  }

}

export default RemoveTeamPageCommand;

