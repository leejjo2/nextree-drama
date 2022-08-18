/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.domain.event;

import io.naraway.accent.domain.trail.DataEvent;
import io.naraway.accent.domain.trail.DataEventType;
import io.naraway.accent.domain.type.NameValueList;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressBookEvent extends DataEvent {
    /* Autogen by nara studio */
    private AddressBook addressBook;
    private String addressBookId;
    private NameValueList nameValues;

    protected AddressBookEvent(DataEventType type) {
        /* Autogen by nara studio */
        super(type);
    }

    public static AddressBookEvent newAddressBookRegisteredEvent(AddressBook addressBook, String addressBookId) {
        /* Autogen by nara studio */
        AddressBookEvent event = new AddressBookEvent(DataEventType.Registered);
        event.setAddressBook(addressBook);
        event.setAddressBookId(addressBookId);
        return event;
    }

    public static AddressBookEvent newAddressBookModifiedEvent(String addressBookId, NameValueList nameValues, AddressBook addressBook) {
        /* Autogen by nara studio */
        AddressBookEvent event = new AddressBookEvent(DataEventType.Modified);
        event.setAddressBookId(addressBookId);
        event.setNameValues(nameValues);
        event.setAddressBook(addressBook);
        return event;
    }

    public static AddressBookEvent newAddressBookRemovedEvent(AddressBook addressBook, String addressBookId) {
        /* Autogen by nara studio */
        AddressBookEvent event = new AddressBookEvent(DataEventType.Removed);
        event.setAddressBook(addressBook);
        event.setAddressBookId(addressBookId);
        return event;
    }

    public String toString() {
        /* Autogen by nara studio */
        return toJson();
    }

    public static AddressBookEvent fromJson(String json) {
        /* Autogen by nara studio */
        return JsonUtil.fromJson(json, AddressBookEvent.class);
    }
}
