package io.naraway.addressbook.facade.api.feature.teambook.command.command;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.trail.CommandRequest;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.feature.teambook.domain.sdo.TeamBookCdo;
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
public class RemoveTeamPageCommand extends CommandRequest {
    //
    private String teamPageId;

    public void validate() {
        //
        Assert.notNull(teamPageId, "teamPageId is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static RemoveTeamPageCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, RemoveTeamPageCommand.class);
    }

    public static RemoveTeamPageCommand sample() {
        //
        return new RemoveTeamPageCommand(
                TeamPageCdo.sample().genId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
