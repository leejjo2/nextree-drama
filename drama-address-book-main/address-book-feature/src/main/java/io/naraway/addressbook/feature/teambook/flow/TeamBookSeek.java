package io.naraway.addressbook.feature.teambook.flow;

import io.naraway.accent.domain.key.tenant.CineroomKey;
import io.naraway.accent.domain.key.tenant.CitizenKey;
import io.naraway.accent.domain.type.Offset;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import io.naraway.addressbook.aggregate.address.domain.logic.AddressBookLogic;
import io.naraway.addressbook.aggregate.address.domain.logic.AddressPageLogic;
import io.naraway.addressbook.feature.teambook.action.TeamBookAction;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TeamBookSeek {
    //
    private final AddressBookLogic addressBookLogic;
    private final AddressPageLogic addressPageLogic;
    private final TeamBookAction teamBookAction;

    public TeamBookSeek(AddressBookLogic addressBookLogic, AddressPageLogic addressPageLogic, TeamBookAction teamBookAction) {
        //
        this.addressBookLogic = addressBookLogic;
        this.addressPageLogic = addressPageLogic;
        this.teamBookAction = teamBookAction;
    }

    public AddressBook findTeamBook(String teamBookId) {
        //
        return addressBookLogic.findAddressBook(teamBookId);
    }

    public List<AddressPage> findTeamPages(String teamBookId) {
        //
        if (!teamBookAction.isIdenticalTeam(teamBookId)) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }
        return addressPageLogic.findByAddressBookId(teamBookId);

    }

    public Page<AddressPage> findTeamPagesByOffset(String teamBookId, Offset offset) {
        //
        if (!teamBookAction.isIdenticalTeam(teamBookId)) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }
        return addressPageLogic.findByAddressBookId(teamBookId, offset);
    }

    public AddressPage findTeamPage(String teamPageId) {
        //
        AddressPage addressPage = addressPageLogic.findAddressPage(teamPageId);
        if (addressPage != null) {
            if (!teamBookAction.isIdenticalTeam(addressPage.getAddressBookId())) {
                throw new IllegalArgumentException("No identical: requester and request info.");
            }
        }
        return addressPage;
    }
}
