package io.naraway.addressbook.feature.teambook.flow;

import io.naraway.accent.domain.type.NameValueList;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressBookCdo;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressPageCdo;
import io.naraway.addressbook.aggregate.address.domain.logic.AddressBookLogic;
import io.naraway.addressbook.aggregate.address.domain.logic.AddressPageLogic;
import io.naraway.addressbook.feature.personalbook.action.PersonalBookAction;
import io.naraway.addressbook.feature.shared.support.NoIdenticalTeamException;
import io.naraway.addressbook.feature.teambook.action.TeamBookAction;
import io.naraway.addressbook.feature.teambook.domain.sdo.TeamBookCdo;
import io.naraway.addressbook.feature.teambook.domain.sdo.TeamPageCdo;
import io.naraway.drama.prologue.spacekeeper.support.DramaRequestContext;
import io.naraway.drama.prologue.spacekeeper.support.NoIdenticalPersonException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TeamBookFlow {
    //
    private final AddressBookLogic addressBookLogic;
    private final AddressPageLogic addressPageLogic;
    private final TeamBookAction teamBookAction;
    private final PersonalBookAction personalBookAction;

    public TeamBookFlow(AddressBookLogic addressBookLogic,
                        AddressPageLogic addressPageLogic,
                        TeamBookAction teamBookAction,
                        PersonalBookAction personalBookAction) {
        //
        this.addressBookLogic = addressBookLogic;
        this.addressPageLogic = addressPageLogic;
        this.teamBookAction = teamBookAction;
        this.personalBookAction = personalBookAction;
    }

    public String registerTeamBook(TeamBookCdo teamBookCdo) {
        //
        String cineroomId = teamBookCdo.getCineroomId();           // Rule: TeamBook id should be a cineroom id
        if (!teamBookAction.isIdenticalTeam(cineroomId)) {
            throw new NoIdenticalTeamException(
                    DramaRequestContext.current().getCineroomIds(),
                    cineroomId
            );
        }

        if (addressBookLogic.existsAddressBook(cineroomId)) {
            throw new DuplicateKeyException("TeamBook already exists: " + cineroomId);
        }

        AddressBookCdo addressBookCdo = teamBookCdo.genAddressBookCdo();
        return addressBookLogic.registerAddressBook(addressBookCdo);
    }

    public void modifyTeamBook(String teamBookId, NameValueList nameValueList) {
        //
        String cineroomId = teamBookId;
        if (!teamBookAction.isIdenticalTeam(cineroomId)) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }

        addressBookLogic.modifyAddressBook(teamBookId, nameValueList);
    }

    public String addTeamPage(TeamPageCdo teamPageCdo) {
        //
        String cineroomId = teamPageCdo.getTeamBookId();
        if (!teamBookAction.isIdenticalTeam(cineroomId)) {
            throw new NoIdenticalTeamException(
                    DramaRequestContext.current().getCineroomIds(),
                    cineroomId
            );
        }

        AddressPageCdo addressPageCdo = teamPageCdo.genAddressPageCdo();
        return addressPageLogic.registerAddressPage(addressPageCdo);
    }

    public void modifyTeamAddressPage(String teamPageId, NameValueList nameValueList) {
        //
        AddressPage addressPage = addressPageLogic.findAddressPage(teamPageId);
        if (addressPage != null) {
            if (!teamBookAction.isIdenticalTeam(addressPage.getAddressBookId())) {
                throw new IllegalArgumentException("No identical: requester and request info.");
            }
            addressPageLogic.modifyAddressPage(teamPageId, nameValueList);
        } else {
            throw new NoSuchElementException("TeamPage id: " + teamPageId);
        }
    }

    public void makeBaseTeamPage(String teamBookId, String teamPageId) {
        //
        String cineroomId = teamBookId;
        if (!teamBookAction.isIdenticalTeam(cineroomId)) {
            throw new NoIdenticalPersonException(
                    DramaRequestContext.current().getCitizenId(),
                    cineroomId
            );
        }

        List<AddressPage> teamPages = addressPageLogic.findByAddressBookIdAndBaseAddress(teamBookId, true);
        for (AddressPage teamPage : teamPages) {
            teamPage.setBaseAddress(false);
            addressPageLogic.modifyAddressPage(teamPage);
        }
        AddressPage targetAddressPage = addressPageLogic.findAddressPage(teamPageId);
        if (targetAddressPage == null) {
            throw new NoSuchElementException("TeamPage id: " + teamPageId);
        }
        targetAddressPage.setBaseAddress(true);
        addressPageLogic.modifyAddressPage(targetAddressPage);
    }

    public void copyTeamPageFromPersonalBook(String teamPageId, String personalBookId) {
        //
        AddressPage addressPage = addressPageLogic.findAddressPage(teamPageId);
        if (addressPage == null) {
            throw new NoSuchElementException("TeamPage id: " + teamPageId);
        }
        if (!teamBookAction.isIdenticalTeam(addressPage.getAddressBookId())) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }

        if (!addressBookLogic.existsAddressBook(personalBookId)) {
            throw new NoSuchElementException("PersonalAddressBook id: " + teamPageId);
        }
        if (DramaRequestContext.current().getRoles().stream().noneMatch(
                role -> Arrays.asList(AddressBookDramaRole.PersonalUser).contains(role))) {
            throw new IllegalArgumentException("Not enough role for requested resource");

        }
        if (!personalBookAction.isIdenticalPerson(personalBookId)) {
            throw new NoIdenticalPersonException(
                    DramaRequestContext.current().getCitizenId(),
                    personalBookId
            );
        }

        addressPage.setAddressBookId(personalBookId);
        addressPage.setBaseAddress(false);
        addressPageLogic.modifyAddressPage(addressPage);
    }

    public void removeTeamBook(String teamBookId) {
        //
        if (!teamBookAction.isIdenticalTeam(teamBookId)) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }
        AddressBook teamBook = addressBookLogic.findAddressBook(teamBookId);
        addressBookLogic.removeAddressBook(teamBook);
        addressPageLogic.removeAllByAddressBookId(teamBookId);
    }

    public void removeTeamPage(String teamPageId) {
        //
        AddressPage teamPage = addressPageLogic.findAddressPage(teamPageId);
        if (!teamBookAction.isIdenticalTeam(teamPage.getAddressBookId())) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }
        addressPageLogic.removeAddressPage(teamPage);
    }
}