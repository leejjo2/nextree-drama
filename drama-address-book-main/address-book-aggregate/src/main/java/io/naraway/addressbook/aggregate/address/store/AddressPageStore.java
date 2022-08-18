/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.store;

import io.naraway.accent.domain.type.Offset;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AddressPageStore {
    /* Autogen by nara studio */
    void create(AddressPage addressPage);
    void createAll(List<AddressPage> addressPages);
    AddressPage retrieve(String id);
    void update(AddressPage addressPage);
    void delete(AddressPage addressPage);
    void delete(String id);
    void deleteAll(List<String> ids);
    boolean exists(String id);

    Page<AddressPage> retrieveByAddressBookId(String addressBookId, Offset offset);
    List<AddressPage> retrieveByAddressBookId(String addressBookId);
    List<AddressPage> retrieveByAddressBookIdAndBaseAddress(String addressBookId, boolean baseAddress);
    void deleteAllByAddressBookId(String addressBookId);
}
