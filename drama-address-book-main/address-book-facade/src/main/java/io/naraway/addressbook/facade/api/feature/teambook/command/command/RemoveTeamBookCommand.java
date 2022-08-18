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

@AuthorizedRole(AddressBookDramaRole.TeamAdmin)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveTeamBookCommand extends CommandRequest {
    //
    private String teamBookId;

    public void validate() {
        //
        Assert.notNull(teamBookId, "teamBookId is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static RemoveTeamBookCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, RemoveTeamBookCommand.class);
    }

    public static RemoveTeamBookCommand sample() {
        //
        return new RemoveTeamBookCommand(
                TeamBookCdo.sample().genId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
