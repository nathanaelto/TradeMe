package io.to.app.application.member;

import io.to.app.domain.MemberId;
import io.to.kernel.Query;

public class RetrieveMemberById implements Query {
    public final MemberId memberId;

    public RetrieveMemberById(MemberId memberId) {
        this.memberId = memberId;
    }
}
