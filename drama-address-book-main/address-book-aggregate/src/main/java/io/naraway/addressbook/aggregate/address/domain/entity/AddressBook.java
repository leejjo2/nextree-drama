/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.domain.entity;

import io.naraway.accent.domain.ddd.DomainAggregate;
import io.naraway.accent.domain.ddd.StageEntity;
import io.naraway.accent.domain.ddd.Updatable;
import io.naraway.accent.domain.key.stage.ActorKey;
import io.naraway.accent.domain.type.NameValue;
import io.naraway.accent.domain.type.NameValueList;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressBookCdo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddressBook extends StageEntity implements DomainAggregate {
    //
    @Updatable
    private String name;
    @Updatable
    private String description;

    transient private List<AddressPage> addressPages;

    public AddressBook(String id, ActorKey requesterKey) {
        //
        super(id, requesterKey);
    }

    public AddressBook(AddressBookCdo addressBookCdo) {
        //
        super(addressBookCdo.genId(), addressBookCdo.getRequesterKey());
        BeanUtils.copyProperties(addressBookCdo, this);
    }

    public static AddressBook newInstance(AddressBookCdo addressBookCdo, NameValueList nameValueList) {
        //
        AddressBook addressBook = new AddressBook(addressBookCdo);
        addressBook.modifyAttributes(nameValueList);

        return addressBook;
    }

    public String toString() {
        //
        return toJson();
    }

    public static AddressBook fromJson(String json) {
        //
        return JsonUtil.fromJson(json, AddressBook.class);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "name":
                    this.name = value;
                    break;
                case "description":
                    this.description = value;
                    break;
                default:
                    throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }

    public static AddressBook sample() {
        //
        return new AddressBook(AddressBookCdo.sample());
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}