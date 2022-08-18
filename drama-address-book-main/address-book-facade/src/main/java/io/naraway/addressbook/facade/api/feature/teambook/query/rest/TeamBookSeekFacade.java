package io.naraway.addressbook.facade.api.feature.teambook.query.rest;

import io.naraway.accent.domain.trail.QueryResponse;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import io.naraway.addressbook.facade.api.feature.teambook.query.query.FindTeamBookQuery;
import io.naraway.addressbook.facade.api.feature.teambook.query.query.FindTeamPageQuery;
import io.naraway.addressbook.facade.api.feature.teambook.query.query.FindTeamPagesByOffsetQuery;
import io.naraway.addressbook.facade.api.feature.teambook.query.query.FindTeamPagesQuery;

import java.util.List;

public interface TeamBookSeekFacade {
    //
    QueryResponse<AddressBook> findTeamBook(FindTeamBookQuery query);
    QueryResponse<List<AddressPage>> findTeamPages(FindTeamPagesQuery query);
    QueryResponse<List<AddressPage>> findTeamPagesByOffset(FindTeamPagesByOffsetQuery query);
    QueryResponse<AddressPage> findTeamPage(FindTeamPageQuery query);
}
