package io.naraway.addressbook.feature.shared.support;

import io.naraway.accent.util.json.JsonUtil;

import java.util.List;

public class NoIdenticalTeamException extends SecurityException {
    //
    public NoIdenticalTeamException() {
    }

    public NoIdenticalTeamException(List<String> currentIds, String requestId) {
        //
        super("No Identical team. currentId = " + JsonUtil.toJson(currentIds) + ", requestId = " + requestId);
    }

    public NoIdenticalTeamException(Throwable cause) {
        //
        super(cause);
    }
}
