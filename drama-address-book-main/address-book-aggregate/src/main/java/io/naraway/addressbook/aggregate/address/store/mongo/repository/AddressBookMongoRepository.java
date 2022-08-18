/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.store.mongo.repository;

import io.naraway.addressbook.aggregate.address.store.mongo.odm.AddressBookDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressBookMongoRepository extends MongoRepository<AddressBookDoc, String> {
    /* Autogen by nara studio */
}
