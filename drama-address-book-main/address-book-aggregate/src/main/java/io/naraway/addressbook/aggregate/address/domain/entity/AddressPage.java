/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.aggregate.address.domain.entity;

import io.naraway.accent.domain.ddd.StageEntity;
import io.naraway.accent.domain.ddd.Updatable;
import io.naraway.accent.domain.key.stage.ActorKey;
import io.naraway.accent.domain.type.NameValue;
import io.naraway.accent.domain.type.NameValueList;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressPageCdo;
import io.naraway.addressbook.aggregate.address.domain.entity.vo.Address;
import io.naraway.addressbook.aggregate.address.domain.entity.vo.Field;
import io.naraway.addressbook.aggregate.address.domain.entity.vo.GeoCoordinate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddressPage extends StageEntity {
    //
    @Updatable
    private GeoCoordinate geoCoordinate;
    @Updatable
    private String name;
    @Updatable
    private Address address;
    @Updatable
    private String phoneNumber;
    @Updatable
    private List<Field> fields;         // Delivery instruction, PersonalCustomId, etc
    @Updatable
    private boolean baseAddress;        // Default Address
    @Updatable
    private String memo;
    @Updatable
    private String addressBookId;

    public AddressPage(String id, ActorKey requesterKey) {
        //
        super(id, requesterKey);
    }

    public AddressPage(AddressPageCdo addressPageCdo) {
        //
        super(addressPageCdo.genId(), addressPageCdo.getRequesterKey());
        BeanUtils.copyProperties(addressPageCdo, this);
        this.baseAddress = false;
    }

    public static AddressPage newInstance(AddressPageCdo addressPageCdo, NameValueList nameValueList) {
        //
        AddressPage addressPage = new AddressPage(addressPageCdo);
        addressPage.modifyAttributes(nameValueList);

        return addressPage;
    }

    public String toString() {
        //
        return toJson();
    }

    public static AddressPage fromJson(String json) {
        //
        return JsonUtil.fromJson(json, AddressPage.class);
    }

    @Override
    protected void modifyAttributes(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "geoCoordinate":
                    this.geoCoordinate = GeoCoordinate.fromJson(value);
                    break;
                case "name":
                    this.name = value;
                    break;
                case "address":
                    this.address = Address.fromJson(value);
                    break;
                case "phoneNumber":
                    this.phoneNumber = value;
                    break;
                case "fields":
                    this.fields = JsonUtil.fromJsonList(value, Field.class);
                    break;
                case "baseAddress":
                    this.baseAddress = Boolean.parseBoolean(value);
                    break;
                case "memo":
                    this.memo = value;
                    break;
                case "addressBookId":
                    this.addressBookId = value;
                    break;
                default:
                    throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }

    public static AddressPage sample() {
        //
        return new AddressPage(AddressPageCdo.sample());
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}