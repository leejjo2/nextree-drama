/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.store.mongo.repository;

import io.naraway.addressbook.aggregate.address.store.mongo.odm.AddressPageDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressPageMongoRepository extends MongoRepository<AddressPageDoc, String> {
    /* Gen by NARA Studio */
    Page<AddressPageDoc> findByAddressBookId(String addressBookId, Pageable pageable);
    List<AddressPageDoc> findByAddressBookId(String addressBookId);
    List<AddressPageDoc> findByAddressBookIdAndBaseAddress(String addressBookId, boolean baseAddress);
    void removeAllByAddressBookId(String addressBookId);
}
