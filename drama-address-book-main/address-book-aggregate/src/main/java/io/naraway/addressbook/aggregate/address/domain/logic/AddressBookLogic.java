/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.domain.logic;

import io.naraway.accent.domain.type.NameValueList;
import io.naraway.accent.util.entity.EntityUtil;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressBookCdo;
import io.naraway.addressbook.aggregate.address.domain.event.AddressBookEvent;
import io.naraway.addressbook.aggregate.address.store.AddressBookStore;
import io.naraway.janitor.proxy.EventProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressBookLogic {
    //
    private final AddressBookStore addressBookStore;
    private final EventProxy eventProxy;

    public AddressBookLogic(AddressBookStore addressBookStore, EventProxy eventProxy) {
        /* Autogen by nara studio */
        this.addressBookStore = addressBookStore;
        this.eventProxy = eventProxy;
    }

    public String registerAddressBook(AddressBookCdo addressBookCdo) {
        /* Autogen by nara studio */
        AddressBook addressBook = new AddressBook(addressBookCdo);
        if (addressBookStore.exists(addressBook.getId())) {
            throw new IllegalArgumentException("addressBook already exists. " + addressBook.getId());
        }
        addressBookStore.create(addressBook);
        AddressBookEvent addressBookEvent = AddressBookEvent.newAddressBookRegisteredEvent(addressBook, addressBook.getId());
        eventProxy.publishEvent(addressBookEvent);
        return addressBook.getId();
    }

    public List<String> registerAddressBooks(List<AddressBookCdo> addressBookCdos) {
        /* Autogen by nara studio */
        return addressBookCdos.stream().map(this::registerAddressBook).collect(Collectors.toList());
    }

    public AddressBook findAddressBook(String addressBookId) {
        /* Autogen by nara studio */
        AddressBook addressBook = addressBookStore.retrieve(addressBookId);
        if (addressBook == null) {
            throw new NoSuchElementException("AddressBook id: " + addressBookId);
        }
        return addressBook;
    }

    public void modifyAddressBook(String addressBookId, NameValueList nameValues) {
        /* Autogen by nara studio */
        AddressBook addressBook = findAddressBook(addressBookId);
        addressBook.modify(nameValues);
        addressBookStore.update(addressBook);
        AddressBookEvent addressBookEvent = AddressBookEvent.newAddressBookModifiedEvent(addressBookId, nameValues, addressBook);
        eventProxy.publishEvent(addressBookEvent);
    }

    public void modifyAddressBook(AddressBook addressBook) {
        /* Autogen by nara studio */
        AddressBook oldAddressBook = findAddressBook(addressBook.getId());
        NameValueList nameValues = EntityUtil.genNameValueList(oldAddressBook, addressBook);
        if (nameValues.size() > 0) {
            modifyAddressBook(addressBook.getId(), nameValues);
        }
    }

    public void removeAddressBook(String addressBookId) {
        /* Autogen by nara studio */
        AddressBook addressBook = findAddressBook(addressBookId);
        addressBookStore.delete(addressBook);
        AddressBookEvent addressBookEvent = AddressBookEvent.newAddressBookRemovedEvent(addressBook, addressBook.getId());
        eventProxy.publishEvent(addressBookEvent);
    }

    public void removeAddressBook(AddressBook addressBook) {
        /* Autogen by nara studio */
        if (addressBook == null) {
            return;
        }
        removeAddressBook(addressBook.getId());
    }

    public boolean existsAddressBook(String addressBookId) {
        /* Autogen by nara studio */
        return addressBookStore.exists(addressBookId);
    }

    public void handleEventForProjection(AddressBookEvent addressBookEvent) {
        /* Autogen by nara studio */
        switch (addressBookEvent.getDataEventType()) {
            case Registered:
                addressBookStore.create(addressBookEvent.getAddressBook());
                break;
            case Modified:
                AddressBook addressBook = addressBookStore.retrieve(addressBookEvent.getAddressBookId());
                addressBook.modify(addressBookEvent.getNameValues());
                addressBookStore.update(addressBook);
                break;
            case Removed:
                addressBookStore.delete(addressBookEvent.getAddressBookId());
                break;
        }
    }
}
