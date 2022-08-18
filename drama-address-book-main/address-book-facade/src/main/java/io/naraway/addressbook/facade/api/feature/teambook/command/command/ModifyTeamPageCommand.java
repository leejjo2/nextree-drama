/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.facade.api.feature.teambook.command.command;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.trail.CommandRequest;
import io.naraway.accent.domain.type.NameValueList;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressPageCdo;
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
public class ModifyTeamPageCommand extends CommandRequest {
    //
    private String teamPageId;
    private NameValueList nameValueList;

    public void validate() {
        //
        Assert.hasText(teamPageId, "teamPageId is required.");
        Assert.notNull(nameValueList, "nameValueList is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static ModifyTeamPageCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ModifyTeamPageCommand.class);
    }

    public static ModifyTeamPageCommand sample() {
        //
        return new ModifyTeamPageCommand(
                AddressPageCdo.sample().genId(),
                NameValueList.newInstance("name", "Team Address Page")
                        .add("phoneNumber", "+82 10-1111-2222")
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}