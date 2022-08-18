package io.naraway.addressbook.facade.api.feature.teambook.query.query;

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

@AuthorizedRole({AddressBookDramaRole.TeamAdmin, AddressBookDramaRole.TeamUser})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindTeamPageQuery extends QueryRequest<AddressPage> {
    //
    private String teamPageId;

    public void validate() {
        //
        Assert.hasText(teamPageId, "teamPageId is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static FindTeamPageQuery fromJson(String json) {
        //
        return JsonUtil.fromJson(json, FindTeamPageQuery.class);
    }

    public static FindTeamPagesQuery sample() {
        //
        return new FindTeamPagesQuery(
                UUID.randomUUID().toString()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
