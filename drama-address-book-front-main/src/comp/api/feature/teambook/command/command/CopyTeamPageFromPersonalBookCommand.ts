import { CommandRequest, CommandType } from '@nara/accent';

class CopyTeamPageFromPersonalBookCommand extends CommandRequest {
  teamPageId: string;

  personalBookId: string;

  constructor(teamPageId: string, personalBookId: string) {
    super(CommandType.UserDefined);
    this.teamPageId = teamPageId;
    this.personalBookId = personalBookId;
  }

  static new(teamPageId: string, personalBookId: string) {
    const command = new CopyTeamPageFromPersonalBookCommand(
      teamPageId,
      personalBookId,
    );

    return command;
  }

}

export default CopyTeamPageFromPersonalBookCommand;

