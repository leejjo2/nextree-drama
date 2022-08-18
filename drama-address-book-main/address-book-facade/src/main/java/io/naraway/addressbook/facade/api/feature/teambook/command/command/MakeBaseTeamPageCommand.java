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
import io.naraway.addressbook.feature.teambook.domain.sdo.TeamBookCdo;
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
public class MakeBaseTeamPageCommand extends CommandRequest {
    //
    private String teamBookId;
    private String teamPageId;

    public void validate() {
        //
        Assert.notNull(teamBookId, "teamBookId is required.");
        Assert.notNull(teamPageId, "teamPageId is required.");

    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static MakeBaseTeamPageCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, MakeBaseTeamPageCommand.class);
    }

    public static MakeBaseTeamPageCommand sample() {
        //
        return new MakeBaseTeamPageCommand(
                TeamBookCdo.sample().genId(),
                UUID.randomUUID().toString()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}