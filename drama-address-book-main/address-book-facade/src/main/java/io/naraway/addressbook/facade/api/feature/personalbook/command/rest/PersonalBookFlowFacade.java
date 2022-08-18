package io.naraway.addressbook.facade.api.feature.personalbook.command.rest;

import io.naraway.accent.domain.trail.CommandResponse;
import io.naraway.addressbook.facade.api.feature.personalbook.command.command.*;

public interface PersonalBookFlowFacade {
    //
    CommandResponse registerPersonalBook(RegisterPersonalBookCommand command);
    CommandResponse modifyPersonalBook(ModifyPersonalBookCommand command);
    CommandResponse addPersonalPage(AddPersonalPageCommand command);
    CommandResponse modifyPersonalPage(ModifyPersonalPageCommand command);
    CommandResponse makeBasePersonalPage(MakeBasePersonalPageCommand command);
    CommandResponse copyPersonalPageFromTeamBook(CopyPersonalPageFromTeamBookCommand command);
    CommandResponse removePersonalBook(RemovePersonalBookCommand command);
    CommandResponse removePersonalPage(RemovePersonalPageCommand command);
}
