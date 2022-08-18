package io.naraway.addressbook.feature.personalbook.flow;

import io.naraway.accent.domain.type.NameValueList;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressBookCdo;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressPageCdo;
import io.naraway.addressbook.aggregate.address.domain.logic.AddressBookLogic;
import io.naraway.addressbook.aggregate.address.domain.logic.AddressPageLogic;
import io.naraway.addressbook.feature.personalbook.action.PersonalBookAction;
import io.naraway.addressbook.feature.personalbook.domain.sdo.PersonalBookCdo;
import io.naraway.addressbook.feature.personalbook.domain.sdo.PersonalPageCdo;
import io.naraway.addressbook.feature.shared.support.NoIdenticalTeamException;
import io.naraway.addressbook.feature.teambook.action.TeamBookAction;
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
public class PersonalBookFlow {
    //
    private final AddressBookLogic addressBookLogic;
    private final AddressPageLogic addressPageLogic;
    private final PersonalBookAction personalBookAction;
    private final TeamBookAction teamBookAction;

    public PersonalBookFlow(AddressBookLogic addressBookLogic,
                            AddressPageLogic addressPageLogic,
                            PersonalBookAction personalBookAction,
                            TeamBookAction teamBookAction) {
        //
        this.addressBookLogic = addressBookLogic;
        this.addressPageLogic = addressPageLogic;
        this.personalBookAction = personalBookAction;
        this.teamBookAction = teamBookAction;
    }

    public String registerPersonalBook(PersonalBookCdo personalBookCdo) {
        //
        String citizenId = personalBookCdo.getCitizenId();           // Rule: AddressBook id should be a citizen id
        if (!personalBookAction.isIdenticalPerson(citizenId)) {
            throw new NoIdenticalPersonException(
                    DramaRequestContext.current().getCitizenId(),
                    citizenId
            );
        }

        if (addressBookLogic.existsAddressBook(citizenId)) {
            throw new DuplicateKeyException("PersonalBook already exists: " + citizenId);
        }

        AddressBookCdo addressBookCdo = personalBookCdo.genAddressBookCdo();
        return addressBookLogic.registerAddressBook(addressBookCdo);
    }

    public void modifyPersonalBook(String personalBookId, NameValueList nameValueList) {
        //
        String citizenId = personalBookId;
        if (!personalBookAction.isIdenticalPerson(citizenId)) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }

        addressBookLogic.modifyAddressBook(personalBookId, nameValueList);
    }

    public String addPersonalPage(PersonalPageCdo personalPageCdo) {
        //
        String citizenId = personalPageCdo.getPersonalBookId();           // Rule: AddressBook id should be a citizen id
        if (!personalBookAction.isIdenticalPerson(citizenId)) {
            throw new NoIdenticalPersonException(
                    DramaRequestContext.current().getCitizenId(),
                    citizenId
            );
        }

        AddressPageCdo addressPageCdo = personalPageCdo.genAddressPageCdo();
        return addressPageLogic.registerAddressPage(addressPageCdo);
    }

    public void modifyPersonalPage(String personalPageId, NameValueList nameValueList) {
        //
        AddressPage personalPage = addressPageLogic.findAddressPage(personalPageId);
        if (personalPage != null) {
            if (!personalBookAction.isIdenticalPerson(personalPage.getAddressBookId())) {
                throw new IllegalArgumentException("No identical: requester and request info.");
            }
            addressPageLogic.modifyAddressPage(personalPageId, nameValueList);
        } else {
            throw new NoSuchElementException("PersonalPage id: " + personalPageId);
        }
    }

    public void makeBasePersonalPage(String personalBookId, String personalPageId) {
        //
        String citizenId = personalBookId;
        if (!personalBookAction.isIdenticalPerson(citizenId)) {
            throw new NoIdenticalPersonException(
                    DramaRequestContext.current().getCitizenId(),
                    citizenId
            );
        }

        List<AddressPage> personalPages = addressPageLogic.findByAddressBookIdAndBaseAddress(personalBookId, true);
        for (AddressPage personalPage : personalPages) {
            personalPage.setBaseAddress(false);
            addressPageLogic.modifyAddressPage(personalPage);
        }

        AddressPage targetAddressPage = addressPageLogic.findAddressPage(personalPageId);
        if (targetAddressPage == null) {
            throw new NoSuchElementException("PersonalPage id: " + personalPageId);
        }
        targetAddressPage.setBaseAddress(true);
        addressPageLogic.modifyAddressPage(targetAddressPage);
    }

    public void copyPersonalPageFromTeamBook(String personalPageId, String teamBookId) {
        //
        AddressPage personalPage = addressPageLogic.findAddressPage(personalPageId);
        if (personalPage == null) {
            throw new NoSuchElementException("PersonalPage id: " + personalPageId);
        }
        if (!personalBookAction.isIdenticalPerson(personalPage.getAddressBookId())) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }

        if (!addressBookLogic.existsAddressBook(teamBookId)) {
            throw new NoSuchElementException("TeamBook id: " + teamBookId);
        }
        if (DramaRequestContext.current().getRoles().stream().noneMatch(
                role -> Arrays.asList(AddressBookDramaRole.TeamUser, AddressBookDramaRole.TeamAdmin).contains(role))) {
            throw new IllegalArgumentException("Not enough role for requested resource");

        }
        if (!teamBookAction.isIdenticalTeam(teamBookId)) {
            throw new NoIdenticalTeamException(
                    DramaRequestContext.current().getCineroomIds(),
                    teamBookId
            );
        }

        personalPage.setAddressBookId(teamBookId);
        personalPage.setBaseAddress(false);
        addressPageLogic.modifyAddressPage(personalPage);
    }

    public void removePersonalBook(String personalBookId) {
        //
        if (!personalBookAction.isIdenticalPerson(personalBookId)) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }
        AddressBook personalBook = addressBookLogic.findAddressBook(personalBookId);
        addressBookLogic.removeAddressBook(personalBook);
        addressPageLogic.removeAllByAddressBookId(personalBookId);
    }

    public void removePersonalPage(String personalPageId) {
        //
        AddressPage personalPage = addressPageLogic.findAddressPage(personalPageId);
        if (!personalBookAction.isIdenticalPerson(personalPage.getAddressBookId())) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }
        addressPageLogic.removeAddressPage(personalPage);
    }
}