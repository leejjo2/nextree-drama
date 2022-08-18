package io.naraway.addressbook.facade.api.feature.teambook.command.rest;

import io.naraway.accent.domain.trail.CommandResponse;
import io.naraway.addressbook.facade.api.feature.teambook.command.command.*;

public interface TeamBookFlowFacade {
    CommandResponse registerTeamBook(RegisterTeamBookCommand command);
    CommandResponse modifyTeamBook(ModifyTeamBookCommand command);
    CommandResponse addTeamPage(AddTeamPageCommand command);
    CommandResponse modifyTeamPage(ModifyTeamPageCommand command);
    CommandResponse makeBaseTeamPage(MakeBaseTeamPageCommand command);
    CommandResponse copyTeamPageFromPersonalBook(CopyTeamPageFromPersonalBookCommand command);
    CommandResponse removeTeamBook(RemoveTeamBookCommand command);
    CommandResponse removeTeamPage(RemoveTeamPageCommand command);
}
