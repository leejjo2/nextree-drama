package io.naraway.addressbook.feature.personalbook.domain.sdo;

import io.naraway.accent.domain.key.tenant.CitizenKey;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressBookCdo;
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
public class PersonalBookCdo extends CreationDataObject {
    //
    private String name;
    private String description;
    private String citizenId;           // Decision is made in Feature layer

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static PersonalBookCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, PersonalBookCdo.class);
    }

    @Override
    public String genId() {
        //
        return citizenId;         // One book per a Citizen
    }

    public AddressBookCdo genAddressBookCdo() {
        //
        return new AddressBookCdo(
                name,
                description,
                genId()
        );
    }

    public static PersonalBookCdo sample() {
        //
        DramaRequestContext.setSampleContext();

        return new PersonalBookCdo(
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
