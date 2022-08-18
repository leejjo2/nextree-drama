/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.facade.api.feature.personalbook.command.command;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.trail.CommandRequest;
import io.naraway.accent.domain.type.NameValueList;
import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.addressbook.aggregate.address.domain.entity.sdo.AddressBookCdo;
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
public class ModifyPersonalBookCommand extends CommandRequest {
    //
    private String personalBookId;
    private NameValueList nameValueList;

    public void validate() {
        //
        Assert.hasText(personalBookId, "personalBookId is required.");
        Assert.notNull(nameValueList, "nameValueList is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static ModifyPersonalBookCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, ModifyPersonalBookCommand.class);
    }

    public static ModifyPersonalBookCommand sample() {
        //
        return new ModifyPersonalBookCommand(
                AddressBookCdo.sample().genId(),
                NameValueList.newInstance("name", "My Address Book")
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}