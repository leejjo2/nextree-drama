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
import io.naraway.addressbook.aggregate.address.domain.entity.vo.GeoCoordinate;
import io.naraway.addressbook.aggregate.address.domain.entity.vo.Address;
import java.util.List;
import io.naraway.addressbook.aggregate.address.domain.entity.vo.Field;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import org.springframework.beans.BeanUtils;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "ADDRESS_PAGE")
public class AddressPageDoc extends StageEntityDoc {
    /* Gen by NARA Studio */
    private GeoCoordinate geoCoordinate; // 
    private String name;
    private Address address;
    private String phoneNumber;
    private List<Field> fields; // Delivery instruction, PersonalCustomId, etc
    private boolean baseAddress; // Default Address
    private String memo;
    private String addressBookId;

    public AddressPageDoc(AddressPage addressPage) {
        /* Gen by NARA Studio */
        super(addressPage);
        BeanUtils.copyProperties(addressPage, this);
    }

    public AddressPage toDomain() {
        /* Gen by NARA Studio */
        AddressPage addressPage = new AddressPage(getId(), genActorKey());
        BeanUtils.copyProperties(this, addressPage);
        return addressPage;
    }

    public static List<AddressPage> toDomains(List<AddressPageDoc> addressPageDocs) {
        /* Gen by NARA Studio */
        return addressPageDocs.stream().map(AddressPageDoc::toDomain).collect(Collectors.toList());
    }

    public static Page<AddressPage> toDomains(Page<AddressPageDoc> addressPageDocsPage) {
        /* Gen by NARA Studio */
        List<AddressPageDoc> addressPageDocs = addressPageDocsPage.getContent();
        List<AddressPage> addressPages = toDomains(addressPageDocs);
        return new PageImpl<>(addressPages, addressPageDocsPage.getPageable(), addressPageDocsPage.getTotalElements());
    }

    public static Slice<AddressPage> toDomains(Slice<AddressPageDoc> addressPageDocsSlice) {
        /* Gen by NARA Studio */
        List<AddressPageDoc> addressPageDocs = addressPageDocsSlice.getContent();
        List<AddressPage> addressPages = toDomains(addressPageDocs);
        return new SliceImpl<>(addressPages, addressPageDocsSlice.getPageable(), addressPageDocsSlice.hasNext());
    }

    public String toString() {
        /* Gen by NARA Studio */
        return toJson();
    }

    public static AddressPageDoc sample() {
        /* Gen by NARA Studio */
        return new AddressPageDoc(AddressPage.sample());
    }

    public static void main(String[] args) {
        /* Gen by NARA Studio */
        System.out.println(sample());
    }
}
