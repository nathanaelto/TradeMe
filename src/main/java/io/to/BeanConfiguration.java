package io.to;

import io.to.app.application.member.*;
import io.to.app.domain.MemberRepository;
import io.to.app.infrastructure.DefaultEventDispatcher;
import io.to.app.infrastructure.InMemoryUserRepository;
import io.to.kernel.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class BeanConfiguration {

    @Bean
    public MemberRepository memberRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public EventDispatcher<Event> eventEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(CreateMemberEvent.class, List.of(new CreateMemberEventListener()));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public CreateMemberCommandHandler createMemberCommandHandler() {
        return new CreateMemberCommandHandler(memberRepository(), eventEventDispatcher());
    }

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(CreateMember.class, new CreateMemberCommandHandler(memberRepository(), eventEventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        queryHandlerMap.put(RetrieveMembers.class, new RetrieveMembersHandler(memberRepository()));
        queryHandlerMap.put(RetrieveMemberById.class, new RetrieveMemberByIdHandler(memberRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    @Bean
    public RetrieveMembersHandler retrieveMembersHandler() {
        return new RetrieveMembersHandler(memberRepository());
    }

    @Bean
    public RetrieveMemberByIdHandler retrieveMemberByIdHandler() {
        return new RetrieveMemberByIdHandler(memberRepository());
    }
}
