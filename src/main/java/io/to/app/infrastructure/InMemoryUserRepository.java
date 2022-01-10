package io.to.app.infrastructure;


import io.to.app.domain.Member;
import io.to.app.domain.MemberId;
import io.to.app.domain.MemberRepository;
import io.to.kernel.NoSuchEntityException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryUserRepository implements MemberRepository {

    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<MemberId, Member> data = new ConcurrentHashMap<>();

    @Override
    public MemberId nextIdentity() {
        return new MemberId(count.incrementAndGet());
    }

    @Override
    public Member findById(MemberId id) {
        final Member user = data.get(id);
        if (user == null) {
            throw NoSuchEntityException.withId(id);
        }
        return user;
    }

    @Override
    public void add(Member user) {
        data.put(user.getId(), user);
    }

    @Override
    public void delete(MemberId id) {
        data.remove(id);
    }

    @Override
    public List<Member> findAll() {
        return List.copyOf(data.values());
    }

}
