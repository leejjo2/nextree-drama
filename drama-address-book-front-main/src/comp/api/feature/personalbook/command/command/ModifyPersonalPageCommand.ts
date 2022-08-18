import { CommandRequest, CommandType, NameValueList } from '@nara/accent';

class ModifyPersonalPageCommand extends CommandRequest {
  personalPageId: string;
  nameValueList: NameValueList;

  constructor(personalPageId: string, nameValueList: NameValueList) {
    super(CommandType.UserDefined);
    this.personalPageId = personalPageId;
    this.nameValueList = nameValueList;
  }

  static new(personalPageId: string, nameValueList: NameValueList) {
    const command = new ModifyPersonalPageCommand(
      personalPageId,
      nameValueList,
    );

    return command;
  }

}

export default ModifyPersonalPageCommand;

