package io.naraway.addressbook.facade.api.feature.personalbook.command.rest;

import io.naraway.accent.domain.trail.CommandResponse;
import io.naraway.accent.domain.type.NameValueList;
import io.naraway.addressbook.facade.api.feature.personalbook.command.command.*;
import io.naraway.addressbook.feature.personalbook.domain.sdo.PersonalBookCdo;
import io.naraway.addressbook.feature.personalbook.domain.sdo.PersonalPageCdo;
import io.naraway.addressbook.feature.personalbook.flow.PersonalBookFlow;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature/personalbook")
public class PersonalBookFlowResource implements PersonalBookFlowFacade {
    //
    private final PersonalBookFlow personalBookFlow;

    public PersonalBookFlowResource(PersonalBookFlow personalBookFlow) {
        //
        this.personalBookFlow = personalBookFlow;
    }

    @Override
    @PostMapping("/register-personal-book/command")
    public CommandResponse registerPersonalBook(@RequestBody RegisterPersonalBookCommand command) {
        //
        command.validate();
        PersonalBookCdo personalBookCdo = command.getPersonalBookCdo();

        String entityId = personalBookFlow.registerPersonalBook(personalBookCdo);
        command.setResponse(entityId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/modify-personal-book/command")
    public CommandResponse modifyPersonalBook(@RequestBody ModifyPersonalBookCommand command) {
        //
        command.validate();
        String personalBookId = command.getPersonalBookId();
        NameValueList nameValueList = command.getNameValueList();

        personalBookFlow.modifyPersonalBook(personalBookId, nameValueList);
        command.setResponse(personalBookId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/add-personal-page/command")
    public CommandResponse addPersonalPage(@RequestBody AddPersonalPageCommand command) {
        //
        command.validate();
        PersonalPageCdo personalPageCdo = command.getPersonalPageCdo();

        String entityId = personalBookFlow.addPersonalPage(personalPageCdo);
        command.setResponse(entityId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/modify-personal-page/command")
    public CommandResponse modifyPersonalPage(@RequestBody ModifyPersonalPageCommand command) {
        //
        command.validate();
        String personalPageId = command.getPersonalPageId();
        NameValueList nameValueList = command.getNameValueList();

        personalBookFlow.modifyPersonalPage(personalPageId, nameValueList);
        command.setResponse(personalPageId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/make-base-personal-page/command")
    public CommandResponse makeBasePersonalPage(@RequestBody MakeBasePersonalPageCommand command) {
        //
        command.validate();
        String personalBookId = command.getPersonalBookId();
        String personalPageId = command.getPersonalPageId();

        personalBookFlow.makeBasePersonalPage(personalBookId, personalPageId);
        command.setResponse(personalPageId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/copy-personal-page-from-team-book/command")
    public CommandResponse copyPersonalPageFromTeamBook(@RequestBody CopyPersonalPageFromTeamBookCommand command) {
        //
        command.validate();
        String personalPageId = command.getPersonalPageId();
        String teamBookId = command.getTeamBookId();

        personalBookFlow.copyPersonalPageFromTeamBook(personalPageId, teamBookId);
        command.setResponse(personalPageId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/remove-personal-book/command")
    public CommandResponse removePersonalBook(@RequestBody RemovePersonalBookCommand command) {
        //
        command.validate();
        String personalBookId = command.getPersonalBookId();

        personalBookFlow.removePersonalBook(personalBookId);
        command.setResponse(personalBookId);
        return command.getResponse();
    }

    @Override
    @PostMapping("/remove-personal-page/command")
    public CommandResponse removePersonalPage(@RequestBody RemovePersonalPageCommand command) {
        //
        command.validate();
        String personalPageId = command.getPersonalPageId();

        personalBookFlow.removePersonalPage(personalPageId);
        command.setResponse(personalPageId);
        return command.getResponse();
    }
}