/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.facade.api.feature.teambook.command.command;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.trail.CommandRequest;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressPageCdo;
import io.naraway.addressbook.feature.teambook.domain.sdo.TeamPageCdo;
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
public class AddTeamPageCommand extends CommandRequest {
    //
    private TeamPageCdo teamPageCdo;

    public void validate() {
        //
        Assert.notNull(teamPageCdo, "teamPageCdo is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static AddTeamPageCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, AddTeamPageCommand.class);
    }

    public static AddTeamPageCommand sample() {
        //
        return new AddTeamPageCommand(
                TeamPageCdo.sample()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}