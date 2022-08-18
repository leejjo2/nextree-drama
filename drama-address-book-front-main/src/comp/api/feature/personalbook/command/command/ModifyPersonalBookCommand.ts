import { CommandRequest, CommandType, NameValueList } from '@nara/accent';

class ModifyPersonalBookCommand extends CommandRequest {
  personalBookId: string;
  nameValueList: NameValueList;

  constructor(personalBookId: string, nameValueList: NameValueList) {
    super(CommandType.UserDefined);
    this.personalBookId = personalBookId;
    this.nameValueList = nameValueList;
  }

  static new(personalBookId: string, nameValueList: NameValueList) {
    const command = new ModifyPersonalBookCommand(
      personalBookId,
      nameValueList,
    );

    return command;
  }

}

export default ModifyPersonalBookCommand;

