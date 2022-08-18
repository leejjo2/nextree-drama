package io.naraway.addressbook.facade.api.feature.personalbook.query.rest;

import io.naraway.accent.domain.trail.QueryResponse;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import io.naraway.addressbook.facade.api.feature.personalbook.query.query.FindPersonalBookQuery;
import io.naraway.addressbook.facade.api.feature.personalbook.query.query.FindPersonalPageQuery;
import io.naraway.addressbook.facade.api.feature.personalbook.query.query.FindPersonalPagesByOffsetQuery;
import io.naraway.addressbook.facade.api.feature.personalbook.query.query.FindPersonalPagesQuery;

import java.util.List;

public interface PersonalBookSeekFacade {
    //
    QueryResponse<AddressBook> findPersonalBook(FindPersonalBookQuery query);
    QueryResponse<List<AddressPage>> findPersonalPages(FindPersonalPagesQuery query);
    QueryResponse<List<AddressPage>> findPersonalPagesByOffset(FindPersonalPagesByOffsetQuery query);
    QueryResponse<AddressPage> findPersonalPage(FindPersonalPageQuery query);
}