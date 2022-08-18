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
public class RegisterTeamBookCommand extends CommandRequest {
    //
    private TeamBookCdo teamBookCdo;

    public void validate() {
        //
        Assert.notNull(teamBookCdo, "teamBookCdo is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static RegisterTeamBookCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, RegisterTeamBookCommand.class);
    }

    public static RegisterTeamBookCommand sample() {
        //
        return new RegisterTeamBookCommand(
                TeamBookCdo.sample()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
