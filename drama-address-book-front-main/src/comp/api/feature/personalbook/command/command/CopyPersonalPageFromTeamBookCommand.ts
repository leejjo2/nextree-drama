import { CommandRequest, CommandType } from '@nara/accent';

class CopyPersonalPageFromTeamBookCommand extends CommandRequest {
  personalPageId: string;
  teamBookId: string;

  constructor(personalPageId: string, teamBookId: string) {
    super(CommandType.UserDefined);
    this.personalPageId = personalPageId;
    this.teamBookId = teamBookId;
  }

  static new(personalPageId: string, teamBookId: string) {
    const command = new CopyPersonalPageFromTeamBookCommand(
      personalPageId,
      teamBookId,
    );

    return command;
  }

}

export default CopyPersonalPageFromTeamBookCommand;

