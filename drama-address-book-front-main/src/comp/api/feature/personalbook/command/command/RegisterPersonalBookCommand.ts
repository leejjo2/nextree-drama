import { CommandRequest, CommandType } from '@nara/accent';
import { PersonalBookCdo } from '../../api-model';

class RegisterPersonalBookCommand extends CommandRequest {
  personalBookCdo: PersonalBookCdo;

  constructor(personalBookCdo: PersonalBookCdo) {
    super(CommandType.UserDefined);
    this.personalBookCdo = personalBookCdo;
  }

  static new(personalBookCdo: PersonalBookCdo) {
    const command = new RegisterPersonalBookCommand(
      personalBookCdo,
    );

    return command;
  }

}

export default RegisterPersonalBookCommand;

