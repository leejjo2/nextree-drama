package io.naraway.addressbook.facade.api.feature.personalbook.query.query;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.trail.QueryRequest;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.UUID;

@AuthorizedRole(AddressBookDramaRole.PersonalUser)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindPersonalPageQuery extends QueryRequest<AddressPage> {
    //
    private String personalPageId;

    public void validate() {
        //
        Assert.hasText(personalPageId, "personalPageId is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static FindPersonalPageQuery fromJson(String json) {
        //
        return JsonUtil.fromJson(json, FindPersonalPageQuery.class);
    }

    public static FindPersonalPageQuery sample() {
        //
        return new FindPersonalPageQuery(
                UUID.randomUUID().toString()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
