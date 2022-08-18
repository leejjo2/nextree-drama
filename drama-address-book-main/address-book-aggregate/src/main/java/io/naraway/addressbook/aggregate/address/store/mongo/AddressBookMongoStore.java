/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.store.mongo;

import io.naraway.accent.domain.type.Offset;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import io.naraway.addressbook.aggregate.address.store.AddressBookStore;
import io.naraway.addressbook.aggregate.address.store.mongo.odm.AddressBookDoc;
import io.naraway.addressbook.aggregate.address.store.mongo.repository.AddressBookMongoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressBookMongoStore implements AddressBookStore {
    /* Autogen by nara studio */
    private final AddressBookMongoRepository addressBookMongoRepository;

    public AddressBookMongoStore(AddressBookMongoRepository addressBookMongoRepository) {
        /* Autogen by nara studio */
        this.addressBookMongoRepository = addressBookMongoRepository;
    }

    @Override
    public void create(AddressBook addressBook) {
        /* Autogen by nara studio */
        AddressBookDoc addressBookDoc = new AddressBookDoc(addressBook);
        addressBookMongoRepository.save(addressBookDoc);
    }

    @Override
    public void createAll(List<AddressBook> addressBooks) {
        /* Autogen by nara studio */
        if (addressBooks == null) {
            return;
        }
        addressBooks.forEach(this::create);
    }

    @Override
    public AddressBook retrieve(String id) {
        /* Autogen by nara studio */
        Optional<AddressBookDoc> addressBookDoc = addressBookMongoRepository.findById(id);
        return addressBookDoc.map(AddressBookDoc::toDomain).orElse(null);
    }

    @Override
    public void update(AddressBook addressBook) {
        /* Autogen by nara studio */
        AddressBookDoc addressBookDoc = new AddressBookDoc(addressBook);
        addressBookMongoRepository.save(addressBookDoc);
    }

    @Override
    public void delete(AddressBook addressBook) {
        /* Autogen by nara studio */
        addressBookMongoRepository.deleteById(addressBook.getId());
    }

    @Override
    public void delete(String id) {
        /* Autogen by nara studio */
        addressBookMongoRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<String> ids) {
        /* Autogen by nara studio */
        if (ids == null) {
            return;
        }
        ids.forEach(this::delete);
    }

    @Override
    public boolean exists(String id) {
        /* Autogen by nara studio */
        return addressBookMongoRepository.existsById(id);
    }

    private Pageable createPageable(Offset offset) {
        /* Autogen by nara studio */
        if (offset.getSortDirection() != null && offset.getSortingField() != null) {
            return PageRequest.of(offset.page(), offset.limit(), (offset.ascendingSort() ? Sort.Direction.ASC : Sort.Direction.DESC), offset.getSortingField());
        } else {
            return PageRequest.of(offset.page(), offset.limit());
        }
    }
}
