package io.to;

import io.to.app.application.member.CreateMember;
import io.to.app.application.member.CreateMemberCommandHandler;
import io.to.app.application.member.RetrieveMembers;
import io.to.app.application.member.RetrieveMembersHandler;
import io.to.app.domain.Member;
import io.to.app.domain.MemberId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringMain {
    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringMain.class, args);

        //--1. Create User
        CreateMemberCommandHandler memberCommandHandler = applicationContext.getBean(CreateMemberCommandHandler.class);
        CreateMember createUser = new CreateMember("TO", "Nathanael", "nto@myges.fr", "azertyuiop", true);
        final MemberId memberId = memberCommandHandler.handle(createUser);

        //--2. Retrieve all users
        RetrieveMembers retrieveMembers = new RetrieveMembers();
        RetrieveMembersHandler retrieveMembersHandler = applicationContext.getBean(RetrieveMembersHandler.class);
        final List<Member> members = retrieveMembersHandler.handle(retrieveMembers);
        members.forEach(System.out::println);

    }
}
