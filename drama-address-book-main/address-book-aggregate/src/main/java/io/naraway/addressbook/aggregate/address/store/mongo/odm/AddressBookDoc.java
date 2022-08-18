/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.store.mongo.odm;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import io.naraway.accent.store.mongo.StageEntityDoc;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "ADDRESS_BOOK")
public class AddressBookDoc extends StageEntityDoc {
    /* Gen by NARA Studio */
    private String name; // 
    private String description;

    public AddressBookDoc(AddressBook addressBook) {
        /* Gen by NARA Studio */
        super(addressBook);
        BeanUtils.copyProperties(addressBook, this);
    }

    public AddressBook toDomain() {
        /* Gen by NARA Studio */
        AddressBook addressBook = new AddressBook(getId(), genActorKey());
        BeanUtils.copyProperties(this, addressBook);
        return addressBook;
    }

    public static List<AddressBook> toDomains(List<AddressBookDoc> addressBookDocs) {
        /* Gen by NARA Studio */
        return addressBookDocs.stream().map(AddressBookDoc::toDomain).collect(Collectors.toList());
    }

    public static Page<AddressBook> toDomains(Page<AddressBookDoc> addressBookDocsPage) {
        /* Gen by NARA Studio */
        List<AddressBookDoc> addressBookDocs = addressBookDocsPage.getContent();
        List<AddressBook> addressBooks = toDomains(addressBookDocs);
        return new PageImpl<>(addressBooks, addressBookDocsPage.getPageable(), addressBookDocsPage.getTotalElements());
    }

    public static Slice<AddressBook> toDomains(Slice<AddressBookDoc> addressBookDocsSlice) {
        /* Gen by NARA Studio */
        List<AddressBookDoc> addressBookDocs = addressBookDocsSlice.getContent();
        List<AddressBook> addressBooks = toDomains(addressBookDocs);
        return new SliceImpl<>(addressBooks, addressBookDocsSlice.getPageable(), addressBookDocsSlice.hasNext());
    }

    public String toString() {
        /* Gen by NARA Studio */
        return toJson();
    }

    public static AddressBookDoc sample() {
        /* Gen by NARA Studio */
        return new AddressBookDoc(AddressBook.sample());
    }

    public static void main(String[] args) {
        /* Gen by NARA Studio */
        System.out.println(sample());
    }
}
