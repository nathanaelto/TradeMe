package io.to.app.domain;

import io.to.kernel.Repository;

import java.util.List;

public interface MemberRepository extends Repository<MemberId, Member> {
    List<Member> findAll();

}