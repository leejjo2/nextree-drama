package io.naraway.addressbook.facade.api.feature.personalbook.command.command;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.trail.CommandRequest;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.feature.personalbook.domain.sdo.PersonalBookCdo;
import io.naraway.addressbook.feature.personalbook.domain.sdo.PersonalPageCdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@AuthorizedRole(AddressBookDramaRole.PersonalUser)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemovePersonalPageCommand extends CommandRequest {
    //
    private String personalPageId;

    public void validate() {
        //
        Assert.notNull(personalPageId, "personalPageId is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static RemovePersonalPageCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, RemovePersonalPageCommand.class);
    }

    public static RemovePersonalPageCommand sample() {
        //
        return new RemovePersonalPageCommand(
                PersonalPageCdo.sample().genId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
