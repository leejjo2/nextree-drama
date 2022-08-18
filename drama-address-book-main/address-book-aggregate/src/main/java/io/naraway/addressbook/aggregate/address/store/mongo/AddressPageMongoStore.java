/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.store.mongo;

import io.naraway.accent.domain.type.Offset;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import io.naraway.addressbook.aggregate.address.store.AddressPageStore;
import io.naraway.addressbook.aggregate.address.store.mongo.odm.AddressPageDoc;
import io.naraway.addressbook.aggregate.address.store.mongo.repository.AddressPageMongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressPageMongoStore implements AddressPageStore {
    /* Gen by NARA Studio */
    private final AddressPageMongoRepository addressPageMongoRepository;

    public AddressPageMongoStore(AddressPageMongoRepository addressPageMongoRepository) {
        /* Gen by NARA Studio */
        this.addressPageMongoRepository = addressPageMongoRepository;
    }

    @Override
    public void create(AddressPage addressPage) {
        /* Gen by NARA Studio */
        AddressPageDoc addressPageDoc = new AddressPageDoc(addressPage);
        addressPageMongoRepository.save(addressPageDoc);
    }

    @Override
    public void createAll(List<AddressPage> addressPages) {
        /* Gen by NARA Studio */
        if (addressPages == null) {
            return;
        }
        addressPages.forEach(this::create);
    }

    @Override
    public AddressPage retrieve(String id) {
        /* Gen by NARA Studio */
        Optional<AddressPageDoc> addressPageDoc = addressPageMongoRepository.findById(id);
        return addressPageDoc.map(AddressPageDoc::toDomain).orElse(null);
    }

    @Override
    public void update(AddressPage addressPage) {
        /* Gen by NARA Studio */
        AddressPageDoc addressPageDoc = new AddressPageDoc(addressPage);
        addressPageMongoRepository.save(addressPageDoc);
    }

    @Override
    public void delete(AddressPage addressPage) {
        /* Gen by NARA Studio */
        addressPageMongoRepository.deleteById(addressPage.getId());
    }

    @Override
    public void delete(String id) {
        /* Gen by NARA Studio */
        addressPageMongoRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<String> ids) {
        /* Gen by NARA Studio */
        if (ids == null) {
            return;
        }
        ids.forEach(this::delete);
    }

    @Override
    public boolean exists(String id) {
        /* Gen by NARA Studio */
        return addressPageMongoRepository.existsById(id);
    }

    private Pageable createPageable(Offset offset) {
        /* Gen by NARA Studio */
        if (offset.getSortDirection() != null && offset.getSortingField() != null) {
            return PageRequest.of(offset.page(), offset.limit(), (offset.ascendingSort() ? Sort.Direction.ASC : Sort.Direction.DESC), offset.getSortingField());
        } else {
            return PageRequest.of(offset.page(), offset.limit());
        }
    }

    @Override
    public Page<AddressPage> retrieveByAddressBookId(String addressBookId, Offset offset) {
        /* Gen by NARA Studio */
        return AddressPageDoc.toDomains(addressPageMongoRepository.findByAddressBookId(addressBookId, createPageable(offset)));
    }

    @Override
    public List<AddressPage> retrieveByAddressBookId(String addressBookId) {
        /* Gen by NARA Studio */
        return AddressPageDoc.toDomains(addressPageMongoRepository.findByAddressBookId(addressBookId));
    }

    @Override
    public List<AddressPage> retrieveByAddressBookIdAndBaseAddress(String addressBookId, boolean baseAddress) {
        /* Gen by NARA Studio */
        return AddressPageDoc.toDomains(addressPageMongoRepository.findByAddressBookIdAndBaseAddress(addressBookId, baseAddress));
    }

    @Override
    public void deleteAllByAddressBookId(String addressBookId) {
        /* Gen by NARA Studio */
        addressPageMongoRepository.removeAllByAddressBookId(addressBookId);
    }
}
