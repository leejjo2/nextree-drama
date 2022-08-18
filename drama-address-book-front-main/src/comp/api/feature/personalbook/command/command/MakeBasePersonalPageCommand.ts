import { CommandRequest, CommandType } from '@nara/accent';

class MakeBasePersonalPageCommand extends CommandRequest {
  personalBookId: string;
  personalPageId: string;

  constructor(personalBookId: string, personalPageId: string) {
    super(CommandType.UserDefined);
    this.personalBookId = personalBookId;
    this.personalPageId = personalPageId;
  }

  static new(personalBookId: string, personalPageId: string) {
    const command = new MakeBasePersonalPageCommand(
      personalBookId,
      personalPageId,
    );

    return command;
  }

}

export default MakeBasePersonalPageCommand;

