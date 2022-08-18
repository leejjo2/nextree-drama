package io.naraway.addressbook.feature.teambook.action;

import io.naraway.drama.prologue.spacekeeper.support.DramaRequestContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class TeamBookAction {
    //
    public boolean isIdenticalTeam(String cineroomId) {
        //
        return !CollectionUtils.isEmpty(DramaRequestContext.current().getCineroomIds())
                && DramaRequestContext.current().getCineroomIds().contains(cineroomId);
    }

}