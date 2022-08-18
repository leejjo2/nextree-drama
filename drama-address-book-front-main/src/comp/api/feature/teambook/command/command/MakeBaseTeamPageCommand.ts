import { CommandRequest, CommandType } from '@nara/accent';

class MakeBaseTeamPageCommand extends CommandRequest {
  teamBookId: string;

  teamPageId: string;

  constructor(teamBookId: string, teamPageId: string) {
    super(CommandType.UserDefined);
    this.teamBookId = teamBookId;
    this.teamPageId = teamPageId;
  }

  static new(teamBookId: string, teamPageId: string) {
    const command = new MakeBaseTeamPageCommand(
      teamBookId,
      teamPageId,
    );

    return command;
  }

}

export default MakeBaseTeamPageCommand;

