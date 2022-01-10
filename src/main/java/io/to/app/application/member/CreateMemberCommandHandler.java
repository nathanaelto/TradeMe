package io.to.app.application.member;

import io.to.app.application.exception.EmailValidationException;
import io.to.app.application.exception.PasswordValidationException;
import io.to.app.domain.Member;
import io.to.app.domain.MemberId;
import io.to.app.domain.MemberRepository;
import io.to.kernel.CommandHandler;
import io.to.kernel.Event;
import io.to.kernel.EventDispatcher;
import org.apache.commons.validator.routines.EmailValidator;

public class CreateMemberCommandHandler implements CommandHandler<CreateMember, MemberId> {
    private final MemberRepository memberRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateMemberCommandHandler(MemberRepository memberRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.memberRepository = memberRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }


    @Override
    public MemberId handle(CreateMember createMember) {

        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid(createMember.email)) {
            throw new EmailValidationException();
        }
        if (createMember.password.length() < 8) {
            throw new PasswordValidationException();
        }

        final MemberId memberId = memberRepository.nextIdentity();
        Member member = new Member(
                memberId,
                createMember.lastname,
                createMember.firstname,
                createMember.email,
                createMember.password,
                createMember.isSubscriber
        );
        memberRepository.add(member);
        eventEventDispatcher.dispatch(new CreateMemberEvent(memberId));
        return memberId;
    }
}
