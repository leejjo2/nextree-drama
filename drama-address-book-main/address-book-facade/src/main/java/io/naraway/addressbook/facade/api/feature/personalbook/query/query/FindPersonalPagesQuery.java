/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.facade.api.feature.personalbook.query.query;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.key.tenant.CitizenKey;
import io.naraway.accent.domain.trail.QueryRequest;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@AuthorizedRole(AddressBookDramaRole.PersonalUser)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindPersonalPagesQuery extends QueryRequest<List<AddressPage>> {
    //
    private String personalBookId;

    public void validate() {
        //
        Assert.hasText(personalBookId, "personalBookId is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static FindPersonalPagesQuery fromJson(String json) {
        //
        return JsonUtil.fromJson(json, FindPersonalPagesQuery.class);
    }

    public static FindPersonalPagesQuery sample() {
        //
        return new FindPersonalPagesQuery(
                CitizenKey.sample().getId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}