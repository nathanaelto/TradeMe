package io.to.app.application.member;

import io.to.app.domain.Member;
import io.to.app.domain.MemberRepository;
import io.to.kernel.QueryHandler;

public class RetrieveMemberByIdHandler implements QueryHandler<RetrieveMemberById, Member> {
    private final MemberRepository memberRepository;

    public RetrieveMemberByIdHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member handle(RetrieveMemberById query) {
        return memberRepository.findById(query.memberId);
    }
}
