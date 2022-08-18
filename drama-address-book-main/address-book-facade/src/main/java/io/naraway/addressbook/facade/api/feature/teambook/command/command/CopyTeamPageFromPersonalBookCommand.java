package io.naraway.addressbook.facade.api.feature.teambook.command.command;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.key.tenant.CineroomKey;
import io.naraway.accent.domain.trail.CommandRequest;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.feature.teambook.domain.sdo.TeamPageCdo;
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
public class CopyTeamPageFromPersonalBookCommand extends CommandRequest {
    //
    private String teamPageId;
    private String personalBookId;

    public void validate() {
        //
        Assert.notNull(teamPageId, "teamPageId is required.");
        Assert.notNull(personalBookId, "personalBookId is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static CopyTeamPageFromPersonalBookCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, CopyTeamPageFromPersonalBookCommand.class);
    }

    public static CopyTeamPageFromPersonalBookCommand sample() {
        //
        return new CopyTeamPageFromPersonalBookCommand(
                TeamPageCdo.sample().genId(),
                CineroomKey.sample().getId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
