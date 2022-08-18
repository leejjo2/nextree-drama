/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.domain.entity.sdo;

import io.naraway.accent.domain.key.tenant.CineroomKey;
import io.naraway.accent.domain.key.tenant.CitizenKey;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.drama.prologue.domain.ddd.CreationDataObject;
import io.naraway.drama.prologue.spacekeeper.support.DramaRequestContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressBookCdo extends CreationDataObject {
    //
    private String name;
    private String description;
    private String addressBookId;           // Decision is made in Feature layer

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static AddressBookCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, AddressBookCdo.class);
    }

    @Override
    public String genId() {
        //
        return addressBookId;         // One book per a Citizen
    }

    public static AddressBookCdo sample() {
        //
        DramaRequestContext.setSampleContext();

        return new AddressBookCdo(
                "Terry's AddressPage Book",
                "Terry's Shipping List",
                CitizenKey.sample().getId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
        System.out.println(sample().genId());
    }
}