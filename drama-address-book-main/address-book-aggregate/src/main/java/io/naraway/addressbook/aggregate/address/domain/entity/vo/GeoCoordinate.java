package io.naraway.addressbook.aggregate.address.domain.entity.vo;

import io.naraway.accent.domain.ddd.ValueObject;
import io.naraway.accent.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeoCoordinate implements ValueObject {
    //
    private String latitude;
    private String longitude;

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static GeoCoordinate fromJson(String json) {
        //
        return JsonUtil.fromJson(json, GeoCoordinate.class);
    }

    public static GeoCoordinate sample() {
        //
        return new GeoCoordinate("37.473083599999995", "126.8788276");
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}