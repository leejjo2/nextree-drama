package io.naraway.addressbook.facade.api.feature.teambook.query.rest;

import io.naraway.accent.domain.trail.QueryResponse;
import io.naraway.accent.domain.type.Offset;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressBook;
import io.naraway.addressbook.aggregate.address.domain.entity.AddressPage;
import io.naraway.addressbook.facade.api.feature.teambook.query.query.FindTeamBookQuery;
import io.naraway.addressbook.facade.api.feature.teambook.query.query.FindTeamPageQuery;
import io.naraway.addressbook.facade.api.feature.teambook.query.query.FindTeamPagesByOffsetQuery;
import io.naraway.addressbook.facade.api.feature.teambook.query.query.FindTeamPagesQuery;
import io.naraway.addressbook.feature.teambook.flow.TeamBookSeek;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feature/teambook")
public class TeamBookSeekResource implements TeamBookSeekFacade {
    //
    private final TeamBookSeek teamBookSeek;

    public TeamBookSeekResource(TeamBookSeek teamBookSeek) {
        //
        this.teamBookSeek = teamBookSeek;
    }

    @Override
    @PostMapping("/find-team-book/query")
    public QueryResponse<AddressBook> findTeamBook(@RequestBody FindTeamBookQuery query) {
        //
        query.validate();
        String teamBookId = query.getTeamBookId();

        AddressBook addressBook = teamBookSeek.findTeamBook(teamBookId);
        query.setResponse(addressBook);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-team-pages/query")
    public QueryResponse<List<AddressPage>> findTeamPages(@RequestBody FindTeamPagesQuery query) {
        //
        query.validate();
        String teamBookId = query.getTeamBookId();

        List<AddressPage> addressPages = teamBookSeek.findTeamPages(teamBookId);
        query.setResponse(addressPages);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-team-pages-by-offset/query")
    public QueryResponse<List<AddressPage>> findTeamPagesByOffset(@RequestBody FindTeamPagesByOffsetQuery query) {
        //
        query.validate();
        String teamBookId = query.getTeamBookId();
        Offset offset = query.getOffset();

        Page<AddressPage> page = teamBookSeek.findTeamPagesByOffset(teamBookId, offset);
        query.setResponse(page);
        return query.getResponse();
    }

    @Override
    @PostMapping("/find-team-page/query")
    public QueryResponse<AddressPage> findTeamPage(@RequestBody FindTeamPageQuery query) {
        //
        query.validate();
        String teamPageId = query.getTeamPageId();

        AddressPage addressPages = teamBookSeek.findTeamPage(teamPageId);
        query.setResponse(addressPages);
        return query.getResponse();
    }
}