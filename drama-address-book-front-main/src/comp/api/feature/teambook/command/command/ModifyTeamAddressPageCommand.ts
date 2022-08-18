import { CommandRequest, CommandType, NameValueList } from '@nara/accent';

class ModifyTeamAddressPageCommand extends CommandRequest {
  teamPageId: string;

  nameValueList: NameValueList;

  constructor(teamPageId: string, nameValueList: NameValueList) {
    super(CommandType.UserDefined);
    this.teamPageId = teamPageId;
    this.nameValueList = nameValueList;
  }

  static new(teamPageId: string, nameValueList: NameValueList) {
    const command = new ModifyTeamAddressPageCommand(
      teamPageId,
      nameValueList,
    );

    return command;
  }

}

export default ModifyTeamAddressPageCommand;

