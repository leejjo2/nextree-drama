package io.naraway.addressbook.feature.personalbook.flow;

import io.naraway.accent.domain.type.Offset;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import io.naraway.addressbook.aggregate.address.domain.logic.AddressBookLogic;
import io.naraway.addressbook.aggregate.address.domain.logic.AddressPageLogic;
import io.naraway.addressbook.feature.personalbook.action.PersonalBookAction;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonalBookSeek {
    //
    private final AddressBookLogic addressBookLogic;
    private final AddressPageLogic addressPageLogic;
    private final PersonalBookAction personalBookAction;

    public PersonalBookSeek(AddressPageLogic addressPageLogic,
                            AddressBookLogic addressBookLogic,
                            PersonalBookAction personalBookAction) {
        //
        this.addressBookLogic = addressBookLogic;
        this.addressPageLogic = addressPageLogic;
        this.personalBookAction = personalBookAction;
    }

    public AddressBook findPersonalBook(String personalBookId) {
        //
        return addressBookLogic.findAddressBook(personalBookId);
    }

    public List<AddressPage> findPersonalPages(String personalBookId) {
        //
        if (!personalBookAction.isIdenticalPerson(personalBookId)) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }
        return addressPageLogic.findByAddressBookId(personalBookId);

    }

    public Page<AddressPage> findPersonalPagesByOffset(String personalBookId, Offset offset) {
        //
        if (!personalBookAction.isIdenticalPerson(personalBookId)) {
            throw new IllegalArgumentException("No identical: requester and request info.");
        }
        return addressPageLogic.findByAddressBookId(personalBookId, offset);
    }

    public AddressPage findPersonalPage(String personalPageId) {
        //
        AddressPage addressPage = addressPageLogic.findAddressPage(personalPageId);
        if (addressPage != null) {
            if (!personalBookAction.isIdenticalPerson(addressPage.getAddressBookId())) {
                throw new IllegalArgumentException("No identical: requester and request info.");
            }
        }
        return addressPage;
    }
}