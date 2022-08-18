package io.naraway.addressbook.facade.api.feature.teambook.command.rest;

import io.naraway.accent.domain.trail.CommandResponse;
import io.naraway.accent.domain.type.NameValueList;
import io.naraway.addressbook.facade.api.feature.teambook.command.command.*;
import io.naraway.addressbook.feature.teambook.domain.sdo.TeamBookCdo;
import io.naraway.addressbook.feature.teambook.domain.sdo.TeamPageCdo;
import io.naraway.addressbook.feature.teambook.flow.TeamBookFlow;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/teambook")
public class TeamBookFlowResource implements TeamBookFlowFacade {
    //
    private final TeamBookFlow teamBookFlow;

    public TeamBookFlowResource(TeamBookFlow teamBookFlow) {
        //
        this.teamBookFlow = teamBookFlow;
    }

    @Override
    @PostMapping("/register-team-book/command")
    public CommandResponse registerTeamBook(@RequestBody RegisterTeamBookCommand command) {
        //
        command.validate();
        TeamBookCdo teamBookCdo = command.getTeamBookCdo();

        String entityId = teamBookFlow.registerTeamBook(teamBookCdo);
        command.setResponse(entityId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/modify-team-book/command")
    public CommandResponse modifyTeamBook(@RequestBody ModifyTeamBookCommand command) {
        //
        command.validate();
        String teamBookId = command.getTeamBookId();
        NameValueList nameValueList = command.getNameValueList();

        teamBookFlow.modifyTeamBook(teamBookId, nameValueList);
        command.setResponse(teamBookId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/add-team-page/command")
    public CommandResponse addTeamPage(@RequestBody AddTeamPageCommand command) {
        //
        command.validate();
        TeamPageCdo teamPageCdo = command.getTeamPageCdo();

        String entityId = teamBookFlow.addTeamPage(teamPageCdo);
        command.setResponse(entityId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/modify-team-page/command")
    public CommandResponse modifyTeamPage(@RequestBody ModifyTeamPageCommand command) {
        //
        command.validate();
        String teamPageId = command.getTeamPageId();
        NameValueList nameValueList = command.getNameValueList();

        teamBookFlow.modifyTeamAddressPage(teamPageId, nameValueList);
        command.setResponse(teamPageId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/make-base-team-page/command")
    public CommandResponse makeBaseTeamPage(@RequestBody MakeBaseTeamPageCommand command) {
        //
        command.validate();
        String teamBookId = command.getTeamBookId();
        String teamPageId = command.getTeamPageId();

        teamBookFlow.makeBaseTeamPage(teamBookId, teamPageId);
        command.setResponse(teamPageId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/copy-team-page-from-personal-book/command")
    public CommandResponse copyTeamPageFromPersonalBook(@RequestBody CopyTeamPageFromPersonalBookCommand command) {
        //
        command.validate();
        String teamPageId = command.getTeamPageId();
        String personalBookId = command.getPersonalBookId();

        teamBookFlow.copyTeamPageFromPersonalBook(teamPageId, personalBookId);
        command.setResponse(teamPageId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/remove-team-book/command")
    public CommandResponse removeTeamBook(@RequestBody RemoveTeamBookCommand command) {
        //
        command.validate();
        String teamBookId = command.getTeamBookId();

        teamBookFlow.removeTeamBook(teamBookId);
        command.setResponse(teamBookId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/remove-team-page/command")
    public CommandResponse removeTeamPage(@RequestBody RemoveTeamPageCommand command) {
        //
        command.validate();
        String teamPageId = command.getTeamPageId();

        teamBookFlow.removeTeamPage(teamPageId);
        command.setResponse(teamPageId);
        return command.getResponse();
    }
}