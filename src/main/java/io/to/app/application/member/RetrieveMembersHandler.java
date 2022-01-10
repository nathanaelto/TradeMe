package io.to.app.application.member;

import io.to.app.domain.Member;
import io.to.app.domain.MemberRepository;
import io.to.kernel.QueryHandler;

import java.util.List;

public class RetrieveMembersHandler implements QueryHandler<RetrieveMembers, List<Member>> {
    private final MemberRepository memberRepository;

    public RetrieveMembersHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public List<Member> handle(RetrieveMembers query) {
        return memberRepository.findAll();
    }
}
