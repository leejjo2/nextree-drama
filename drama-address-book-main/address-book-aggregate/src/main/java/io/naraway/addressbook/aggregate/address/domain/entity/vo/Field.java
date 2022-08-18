package io.naraway.addressbook.aggregate.address.domain.entity.vo;

import io.naraway.accent.domain.ddd.ValueObject;
import io.naraway.accent.util.json.JsonSerializable;
import io.naraway.accent.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Field implements ValueObject {
    //
    private String name;
    private String value;
    private String descriptions;

    public Field(String name, String value) {
        //
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static Field fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Field.class);
    }

    public static Field sample() {
        //
        Field field = new Field(
                "ID Number",
                "12345678",
                "Terry's Personal Customs Clearance Code (개인통관고유부호)"
        );

        return field;
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}