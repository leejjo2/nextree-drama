package io.naraway.addressbook.aggregate;

import io.naraway.accent.domain.key.kollection.DramaRole;
import io.naraway.accent.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddressBookDramaRole {
    //
    public static final String Director = "director";
    public static final String PersonalUser = "personal-user";
    public static final String TeamAdmin = "team-admin";
    public static final String TeamUser = "team-user";

    private List<DramaRole> roles;

    public static void validate(String json) {
        //
        AddressBookDramaRole drama = JsonUtil.fromJson(json, AddressBookDramaRole.class);
        drama.validate();
    }

    public void validate() {
        //
        Assert.notNull(this.roles, "'roles' is required");

        if (roles.stream().noneMatch(role -> role.getCode().equals(Director))) {
            throw new IllegalArgumentException("drama role is missing, role = " + Director);
        }
        if (roles.stream().noneMatch(role -> role.getCode().equals(PersonalUser))) {
            throw new IllegalArgumentException("drama role is missing, role = " + PersonalUser);
        }
        if (roles.stream().noneMatch(role -> role.getCode().equals(TeamAdmin))) {
            throw new IllegalArgumentException("drama role is missing, role = " + TeamAdmin);
        }
        if (roles.stream().noneMatch(role -> role.getCode().equals(TeamUser))) {
            throw new IllegalArgumentException("drama role is missing, role = " + TeamUser);
        }
    }
}