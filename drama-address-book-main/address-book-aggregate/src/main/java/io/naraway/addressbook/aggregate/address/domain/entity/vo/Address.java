package io.naraway.addressbook.aggregate.address.domain.entity.vo;

import io.naraway.accent.domain.ddd.ValueObject;
import io.naraway.accent.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Address implements ValueObject {
    //
    private String zipCode;
    private String zipAddress;

    private String city;
    private String state;

    private String street;              // non-zip personaladdress, or street personaladdress
    private String country;

    public Address(String zipCode, String country) {
        //
        this.zipCode = zipCode;
        this.country = country;
    }

    public static Address ofKorean(String zipCode,
                                   String zipAddress,
                                   String street,
                                   String country) {
        //
        Address address = new Address(zipCode, country);
        address.setZipAddress(zipAddress);
        address.setStreet(street);

        return address;
    }

    public static Address ofUS(String zipCode,
                               String street,
                               String city,
                               String state,
                               String country) {
        //
        Address address = new Address(zipCode, country);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);

        return address;
    }

    public static Address ofUzbek(String zipCode,
                                  String street,
                                  String city,
                                  String country) {
        //
        Address address = new Address(zipCode, country);
        address.setStreet(street);
        address.setCity(city);

        return address;
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static Address fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Address.class);
    }

    public static Address sampleUS() {
        //
        String zipCode = "06889";
        String street = "12 Jhones st.";
        String city = "fairfield";
        String state = "CT";
        String country = "U.S.A";

        return Address.ofUS(zipCode, street, city, state, country);
    }

    public static Address sampleKorean() {
        //
        String zipCode = "12345";
        String zipAddress = "서울시 금천구 디지털1로 155번지 잼잼빌딩";
        String street = "703호";
        String country = "대한민국";

        return Address.ofKorean(zipCode, zipAddress, street, country);
    }

    public static Address sampleUzbek() {
        //
        String zipCode = "100004";
        String street = "1, Chust street";
        String city = "Mirzo Ulugbek district, Tashkent";
        String country = "Uzbekistan";

        return Address.ofUzbek(zipCode, street, city, country);
    }

    public static void main(String[] args) {
        //
        System.out.println(sampleUS().toPrettyJson());
        System.out.println(sampleKorean().toPrettyJson());
        System.out.println(sampleUzbek().toPrettyJson());
    }
}
