import { CommandRequest, CommandType } from '@nara/accent';

class RemovePersonalPageCommand extends CommandRequest {
  personalPageId: string;

  constructor(personalPageId: string) {
    super(CommandType.UserDefined);
    this.personalPageId = personalPageId;
  }

  static new(personalPageId: string) {
    const command = new RemovePersonalPageCommand(
      personalPageId,
    );

    return command;
  }

}

export default RemovePersonalPageCommand;

