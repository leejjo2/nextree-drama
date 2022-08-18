package io.naraway.addressbook.facade.api.feature.personalbook.query.rest;

import io.naraway.accent.domain.trail.QueryResponse;
import io.naraway.accent.domain.type.Offset;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import io.naraway.addressbook.facade.api.feature.personalbook.query.query.FindPersonalBookQuery;
import io.naraway.addressbook.facade.api.feature.personalbook.query.query.FindPersonalPageQuery;
import io.naraway.addressbook.facade.api.feature.personalbook.query.query.FindPersonalPagesByOffsetQuery;
import io.naraway.addressbook.facade.api.feature.personalbook.query.query.FindPersonalPagesQuery;
import io.naraway.addressbook.feature.personalbook.flow.PersonalBookSeek;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feature/personalbook")
public class PersonalBookSeekResource implements PersonalBookSeekFacade {
    //
    private final PersonalBookSeek personalBookSeek;

    public PersonalBookSeekResource(PersonalBookSeek personalBookSeek) {
        //
        this.personalBookSeek = personalBookSeek;
    }

    @Override
    @PostMapping("/find-personal-book/query")
    public QueryResponse<AddressBook> findPersonalBook(@RequestBody FindPersonalBookQuery query) {
        //
        query.validate();
        String personalBookId = query.getPersonalBookId();

        AddressBook addressBook = personalBookSeek.findPersonalBook(personalBookId);
        query.setResponse(addressBook);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-personal-pages/query")
    public QueryResponse<List<AddressPage>> findPersonalPages(@RequestBody FindPersonalPagesQuery query) {
        //
        query.validate();
        String personalBookId = query.getPersonalBookId();

        List<AddressPage> addressPages = personalBookSeek.findPersonalPages(personalBookId);
        query.setResponse(addressPages);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-personal-pages-by-offset/query")
    public QueryResponse<List<AddressPage>> findPersonalPagesByOffset(@RequestBody FindPersonalPagesByOffsetQuery query) {
        //
        query.validate();
        String personalBookId = query.getPersonalBookId();
        Offset offset = query.getOffset();


        Page<AddressPage> page = personalBookSeek.findPersonalPagesByOffset(personalBookId, offset);
        query.setResponse(page);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-personal-page/query")
    public QueryResponse<AddressPage> findPersonalPage(@RequestBody FindPersonalPageQuery query) {
        //
        query.validate();
        String personalPageId = query.getPersonalPageId();

        AddressPage addressPages = personalBookSeek.findPersonalPage(personalPageId);
        query.setResponse(addressPages);
        return query.getResponse();
    }
}