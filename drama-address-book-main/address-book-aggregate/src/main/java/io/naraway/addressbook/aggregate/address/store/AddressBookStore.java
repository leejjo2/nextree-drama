/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.store;

import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;

import java.util.List;

public interface AddressBookStore {
    /* Autogen by nara studio */
    void create(AddressBook addressBook);
    void createAll(List<AddressBook> addressBooks);
    AddressBook retrieve(String id);
    void update(AddressBook addressBook);
    void delete(AddressBook addressBook);
    void delete(String id);
    void deleteAll(List<String> ids);
    boolean exists(String id);
}