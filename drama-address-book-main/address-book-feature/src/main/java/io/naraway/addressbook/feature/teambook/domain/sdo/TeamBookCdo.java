package io.naraway.addressbook.feature.teambook.domain.sdo;

import io.naraway.accent.domain.key.tenant.CineroomKey;
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
public class TeamBookCdo extends CreationDataObject {
    //
    private String name;
    private String description;
    private String cineroomId;           // Decision is made in Feature layer

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static TeamBookCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, TeamBookCdo.class);
    }

    @Override
    public String genId() {
        //
        return cineroomId;         // One book per a Citizen
    }

    public AddressBookCdo genAddressBookCdo() {
        //
        return new AddressBookCdo(
                name,
                description,
                genId()
        );
    }

    public static TeamBookCdo sample() {
        //
        DramaRequestContext.setSampleContext();

        return new TeamBookCdo(
                "Namoosori AddressPage Book",
                "Namoosori Address List",
                CineroomKey.sample().getId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
        System.out.println(sample().genId());
    }
}
