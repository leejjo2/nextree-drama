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
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressPageEvent extends DataEvent {
    /* Autogen by nara studio */
    private AddressPage addressPage;
    private String addressPageId;
    private NameValueList nameValues;

    protected AddressPageEvent(DataEventType type) {
        /* Autogen by nara studio */
        super(type);
    }

    public static AddressPageEvent newAddressPageRegisteredEvent(AddressPage addressPage, String addressPageId) {
        /* Autogen by nara studio */
        AddressPageEvent event = new AddressPageEvent(DataEventType.Registered);
        event.setAddressPage(addressPage);
        event.setAddressPageId(addressPageId);
        return event;
    }

    public static AddressPageEvent newAddressPageModifiedEvent(String addressPageId, NameValueList nameValues, AddressPage addressPage) {
        /* Autogen by nara studio */
        AddressPageEvent event = new AddressPageEvent(DataEventType.Modified);
        event.setAddressPageId(addressPageId);
        event.setNameValues(nameValues);
        event.setAddressPage(addressPage);
        return event;
    }

    public static AddressPageEvent newAddressPageRemovedEvent(AddressPage addressPage, String addressPageId) {
        /* Autogen by nara studio */
        AddressPageEvent event = new AddressPageEvent(DataEventType.Removed);
        event.setAddressPage(addressPage);
        event.setAddressPageId(addressPageId);
        return event;
    }

    public String toString() {
        /* Autogen by nara studio */
        return toJson();
    }

    public static AddressPageEvent fromJson(String json) {
        /* Autogen by nara studio */
        return JsonUtil.fromJson(json, AddressPageEvent.class);
    }
}
