package io.to.app.application.member;

import io.to.kernel.Command;

public class CreateMember implements Command {
    public final String lastname;
    public final String firstname;
    public final String email;
    public final String password;
    public final boolean isSubscriber;

    public CreateMember(String lastname, String firstname, String email, String password, boolean isSubscriber) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.isSubscriber = isSubscriber;
    }
}
