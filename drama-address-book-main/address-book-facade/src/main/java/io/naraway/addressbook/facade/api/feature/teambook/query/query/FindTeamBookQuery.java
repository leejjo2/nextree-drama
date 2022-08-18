package io.naraway.addressbook.facade.api.feature.teambook.query.query;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.key.tenant.CineroomKey;
import io.naraway.accent.domain.trail.QueryRequest;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@AuthorizedRole({AddressBookDramaRole.TeamAdmin, AddressBookDramaRole.TeamUser})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindTeamBookQuery extends QueryRequest<AddressBook> {
    //
    private String teamBookId;

    public void validate() {
        //
        Assert.hasText(teamBookId, "teamBookId is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static FindTeamBookQuery fromJson(String json) {
        //
        return JsonUtil.fromJson(json, FindTeamBookQuery.class);
    }

    public static FindTeamBookQuery sample() {
        //
        return new FindTeamBookQuery(
                CineroomKey.sample().getId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
