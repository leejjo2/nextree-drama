import { CommandRequest, CommandType, NameValueList } from '@nara/accent';

class ModifyTeamBookCommand extends CommandRequest {
  teamBookId: string;

  nameValueList: NameValueList;

  constructor(teamBookId: string, nameValueList: NameValueList) {
    super(CommandType.UserDefined);
    this.teamBookId = teamBookId;
    this.nameValueList = nameValueList;
  }

  static new(teamBookId: string, nameValueList: NameValueList) {
    const command = new ModifyTeamBookCommand(
      teamBookId,
      nameValueList,
    );

    return command;
  }

}

export default ModifyTeamBookCommand;

