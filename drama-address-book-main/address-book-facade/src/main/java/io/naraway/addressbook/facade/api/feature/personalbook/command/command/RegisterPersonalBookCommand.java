/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.facade.api.feature.personalbook.command.command;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.trail.CommandRequest;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
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
public class RegisterPersonalBookCommand extends CommandRequest {
    //
    private PersonalBookCdo personalBookCdo;

    public void validate() {
        //
        Assert.notNull(personalBookCdo, "personalBookCdo is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static RegisterPersonalBookCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, RegisterPersonalBookCommand.class);
    }

    public static RegisterPersonalBookCommand sample() {
        //
        return new RegisterPersonalBookCommand(
                PersonalBookCdo.sample()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}