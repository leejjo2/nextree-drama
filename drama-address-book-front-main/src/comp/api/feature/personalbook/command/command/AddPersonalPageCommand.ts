import { CommandRequest, CommandType } from '@nara/accent';
import { PersonalPageCdo } from '../../api-model';

class AddPersonalPageCommand extends CommandRequest {
  personalPageCdo: PersonalPageCdo;

  constructor(personalPageCdo: PersonalPageCdo) {
    super(CommandType.UserDefined);
    this.personalPageCdo = personalPageCdo;
  }

  static new(personalPageCdo: PersonalPageCdo) {
    const command = new AddPersonalPageCommand(
      personalPageCdo,
    );

    return command;
  }

}

export default AddPersonalPageCommand;

