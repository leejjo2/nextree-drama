package io.naraway.addressbook.aggregate.address.domain.entity.sdo;

import io.naraway.accent.util.json.JsonUtil;
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
public class AddressPageCdo extends CreationDataObject {
    //
    private String name;
    private Address address;
    private String phoneNumber;
    private List<Field> fields;         // Delivery instruction, PersonalCustomId, etc
    private String addressBookId;

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
        return super.genId();
    }

    public static AddressPageCdo sample() {
        //
        DramaRequestContext.setSampleContext();

        return new AddressPageCdo(
                "Home",
                Address.sampleKorean(),
                "+82 10-9999-9999",
                Arrays.asList(Field.sample()),
                AddressBookCdo.sample().genId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}