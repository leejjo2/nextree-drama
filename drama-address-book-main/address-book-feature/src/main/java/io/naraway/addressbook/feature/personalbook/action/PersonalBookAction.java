package io.naraway.addressbook.feature.personalbook.action;

import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import io.naraway.drama.prologue.spacekeeper.support.DramaRequestContext;
import io.naraway.drama.prologue.spacekeeper.support.NoIdenticalPersonException;
import io.naraway.drama.prologue.spacekeeper.support.NoSuchRoleException;
import org.springframework.stereotype.Component;

@Component
public class PersonalBookAction {
    //
    public void example(String addressBookId) {
        if (!hasRole(AddressBookDramaRole.PersonalUser)) {
            throw new NoSuchRoleException(
                    DramaRequestContext.current().getRoles(),
                    AddressBookDramaRole.PersonalUser
            );
        }

        // Real code
        if (!isIdenticalPerson(addressBookId)) {
            throw new NoIdenticalPersonException(
                    DramaRequestContext.current().getCitizenId(),
                    addressBookId
            );
        }
    }

    public boolean isIdenticalPerson(String citizenId) {
        //
        return citizenId.equals(DramaRequestContext.current().getCitizenId());
    }

    public boolean hasRole(String role) {
        //
        return DramaRequestContext.current().hasRole(role);
    }
}