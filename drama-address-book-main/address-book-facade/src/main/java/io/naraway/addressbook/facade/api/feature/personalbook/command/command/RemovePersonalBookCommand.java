package io.naraway.addressbook.facade.api.feature.personalbook.command.command;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.trail.CommandRequest;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import io.naraway.addressbook.feature.personalbook.domain.sdo.PersonalBookCdo;
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
public class RemovePersonalBookCommand extends CommandRequest {
    //
    private String personalBookId;

    public void validate() {
        //
        Assert.notNull(personalBookId, "personalBookId is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static RemovePersonalBookCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, RemovePersonalBookCommand.class);
    }

    public static RemovePersonalBookCommand sample() {
        //
        return new RemovePersonalBookCommand(
                PersonalBookCdo.sample().genId()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}
