package io.naraway.addressbook.feature.teambook.domain.sdo;

import io.naraway.accent.domain.key.tenant.CitizenKey;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressPageCdo;
import io.naraway.addressbook.aggregate.address.domain.entity.vo.Address;
import io.naraway.addressbook.aggregate.address.domain.entity.vo.Field;
import io.naraway.drama.prologue.domain.ddd.CreationDataObject;
import io.naraway.drama.prologue.spacekeeper.support.DramaRequestContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamPageCdo extends CreationDataObject {
    //
    private String name;
    private Address address;
    private String phoneNumber;
    private List<Field> fields;
    private String teamBookId;

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static TeamPageCdo fromJson(String json) {
        //
        return JsonUtil.fromJson(json, TeamPageCdo.class);
    }

    @Override
    public String genId() {
        //
        return super.genId();
    }

    public AddressPageCdo genAddressPageCdo() {
        //
        return new AddressPageCdo(
                name,
                address,
                phoneNumber,
                fields,
                teamBookId
        );
    }

    public static TeamPageCdo sample() {
        //
        DramaRequestContext.setSampleContext();

        return new TeamPageCdo(
                "Namoosori",
                Address.sampleKorean(),
                "+82 2-9999-9999",
                Arrays.asList(Field.sample()),
                CitizenKey.sample().getId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}