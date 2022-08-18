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
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressBookCdo;
import io.naraway.addressbook.feature.teambook.domain.sdo.TeamBookCdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;


@AuthorizedRole(AddressBookDramaRole.TeamAdmin)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyTeamBookCommand extends CommandRequest {
    //
    private String teamBookId;
    private NameValueList nameValueList;

    public void validate() {
        //
        Assert.hasText(teamBookId, "teamBookId is required.");
        Assert.notNull(nameValueList, "nameValueList is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static ModifyTeamBookCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ModifyTeamBookCommand.class);
    }

    public static ModifyTeamBookCommand sample() {
        //
        return new ModifyTeamBookCommand(
                TeamBookCdo.sample().genId(),
                NameValueList.newInstance("name", "Namoosori AddressPage Book")
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}