/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.domain.logic;

import io.naraway.accent.domain.key.tenant.AudienceKey;
import io.naraway.accent.domain.type.NameValueList;
import io.naraway.accent.domain.type.Offset;
import io.naraway.accent.util.entity.EntityUtil;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressPageCdo;
import io.naraway.addressbook.aggregate.address.domain.event.AddressPageEvent;
import io.naraway.addressbook.aggregate.address.store.AddressPageStore;
import io.naraway.janitor.proxy.EventProxy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressPageLogic {
    /* Gen by NARA Studio */
    private final AddressBookLogic addressBookLogic; // 
    private final AddressPageStore addressPageStore;
    private final EventProxy eventProxy;

    public AddressPageLogic(AddressBookLogic addressBookLogic, AddressPageStore addressPageStore, EventProxy eventProxy) {
        /* Gen by NARA Studio */
        this.addressBookLogic = addressBookLogic;
        this.addressPageStore = addressPageStore;
        this.eventProxy = eventProxy;
    }

    public String registerAddressPage(AddressPageCdo addressPageCdo) {
        /* Autogen by nara studio */
        AudienceKey audienceKey = new AudienceKey();
        AddressPage addressPage = new AddressPage(addressPageCdo);
        if (addressPageStore.retrieve(addressPage.getId()) != null) {
            throw new IllegalArgumentException("addressPage already exists. " + addressPage.getId());
        }
        addressPageStore.create(addressPage);
        AddressPageEvent addressPageEvent = AddressPageEvent.newAddressPageRegisteredEvent(addressPage, addressPage.getId());
        eventProxy.publishEvent(addressPageEvent);
        return addressPage.getId();
    }

    public String registerAddressPage(AddressPage addressPage) {
        if (/* Autogen by nara studio */
        addressPageStore.exists(addressPage.getId())) {
            throw new IllegalArgumentException("addressPage already exists. " + addressPage.getId());
        }
        addressPageStore.create(addressPage);
        AddressPageEvent addressPageEvent = AddressPageEvent.newAddressPageRegisteredEvent(addressPage, addressPage.getId());
        eventProxy.publishEvent(addressPageEvent);
        return addressPage.getId();
    }

    public List<String> registerAddressPages(List<AddressPageCdo> addressPageCdos) {
        return /* Autogen by nara studio */
        addressPageCdos.stream().map(this::registerAddressPage).collect(Collectors.toList());
    }

    public AddressPage findAddressPage(String addressPageId) {
        /* Autogen by nara studio */
        AddressPage addressPage = addressPageStore.retrieve(addressPageId);
        if (addressPage == null) {
            throw new NoSuchElementException("AddressPage id: " + addressPageId);
        }
        return addressPage;
    }

    public void modifyAddressPage(String addressPageId, NameValueList nameValues) {
        /* Autogen by nara studio */
        AddressPage addressPage = findAddressPage(addressPageId);
        addressPage.modify(nameValues);
        addressPageStore.update(addressPage);
        AddressPageEvent addressPageEvent = AddressPageEvent.newAddressPageModifiedEvent(addressPageId, nameValues, addressPage);
        eventProxy.publishEvent(addressPageEvent);
    }

    public void modifyAddressPage(AddressPage addressPage) {
        /* Autogen by nara studio */
        AddressPage oldAddressPage = findAddressPage(addressPage.getId());
        NameValueList nameValues = EntityUtil.genNameValueList(oldAddressPage, addressPage);
        if (nameValues.size() > 0) {
            modifyAddressPage(addressPage.getId(), nameValues);
        }
        AddressPageEvent addressPageEvent = AddressPageEvent.newAddressPageModifiedEvent(addressPage.getId(), nameValues, addressPage);
        eventProxy.publishEvent(addressPageEvent);
    }

    public void removeAddressPage(String addressPageId) {
        /* Autogen by nara studio */
        AddressPage addressPage = findAddressPage(addressPageId);
        addressPageStore.delete(addressPage);
        AddressPageEvent addressPageEvent = AddressPageEvent.newAddressPageRemovedEvent(addressPage, addressPage.getId());
        eventProxy.publishEvent(addressPageEvent);
    }

    public boolean existsAddressPage(String addressPageId) {
        return /* Autogen by nara studio */
        addressPageStore.exists(addressPageId);
    }

    public void removeAddressPage(AddressPage addressPage) {
        if (/* Autogen by nara studio */
        addressPage == null) {
            return;
        }
        removeAddressPage(addressPage.getId());
    }

    public void handleEventForProjection(AddressPageEvent addressPageEvent) {
        switch(/* Autogen by nara studio */
        addressPageEvent.getDataEventType()) {
            case Registered:
                addressPageStore.create(addressPageEvent.getAddressPage());
                break;
            case Modified:
                AddressPage addressPage = addressPageStore.retrieve(addressPageEvent.getAddressPageId());
                addressPage.modify(addressPageEvent.getNameValues());
                addressPageStore.update(addressPage);
                break;
            case Removed:
                addressPageStore.delete(addressPageEvent.getAddressPageId());
                break;
        }
    }

    public Page<AddressPage> findByAddressBookId(String addressBookId, Offset offset) {
        /* Gen by NARA Studio */
        return addressPageStore.retrieveByAddressBookId(addressBookId, offset);
    }

    public List<AddressPage> findByAddressBookId(String addressBookId) {
        /* Gen by NARA Studio */
        return addressPageStore.retrieveByAddressBookId(addressBookId);
    }

    public List<AddressPage> findByAddressBookIdAndBaseAddress(String addressBookId, boolean baseAddress) {
        /* Gen by NARA Studio */
        return addressPageStore.retrieveByAddressBookIdAndBaseAddress(addressBookId, baseAddress);
    }

    public void removeAllByAddressBookId(String addressBookId) {
        /* Gen by NARA Studio */
        addressPageStore.deleteAllByAddressBookId(addressBookId);
    }
}
