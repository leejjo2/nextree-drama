package io.naraway.addressbook.facade.api.feature.personalbook.command.command;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.key.tenant.CineroomKey;
import io.naraway.accent.domain.trail.CommandRequest;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.UUID;

@AuthorizedRole(AddressBookDramaRole.PersonalUser)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CopyPersonalPageFromTeamBookCommand extends CommandRequest {
    //
    private String personalPageId;
    private String teamBookId;

    public void validate() {
        //
        Assert.notNull(personalPageId, "personalPageId is required.");
        Assert.notNull(teamBookId, "teamBookId is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static CopyPersonalPageFromTeamBookCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, CopyPersonalPageFromTeamBookCommand.class);
    }

    public static CopyPersonalPageFromTeamBookCommand sample() {
        //
        return new CopyPersonalPageFromTeamBookCommand(
                UUID.randomUUID().toString(),
                CineroomKey.sample().getId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
