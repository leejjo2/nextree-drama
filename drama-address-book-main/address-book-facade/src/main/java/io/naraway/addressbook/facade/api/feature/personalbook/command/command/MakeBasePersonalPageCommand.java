/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook.facade.api.feature.personalbook.command.command;

import io.naraway.accent.domain.ddd.AuthorizedRole;
import io.naraway.accent.domain.key.tenant.CitizenKey;
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
public class MakeBasePersonalPageCommand extends CommandRequest {
    //
    private String personalBookId;
    private String personalPageId;

    public void validate() {
        //
        Assert.notNull(personalBookId, "personalBookId is required.");
        Assert.notNull(personalPageId, "personalPageId is required.");
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static MakeBasePersonalPageCommand fromJson(String json) {
        //
        return JsonUtil.fromJson(json, MakeBasePersonalPageCommand.class);
    }

    public static MakeBasePersonalPageCommand sample() {
        //
        return new MakeBasePersonalPageCommand(
                CitizenKey.sample().getId(),
                UUID.randomUUID().toString()
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample().toPrettyJson());
    }
}