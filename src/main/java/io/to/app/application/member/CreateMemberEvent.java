package io.to.app.application.member;

import io.to.app.domain.MemberId;
import io.to.kernel.ApplicationEvent;

public class CreateMemberEvent implements ApplicationEvent{
    private final MemberId memberId;

    public CreateMemberEvent(MemberId memberId) {
        this.memberId = memberId;
    }
}
