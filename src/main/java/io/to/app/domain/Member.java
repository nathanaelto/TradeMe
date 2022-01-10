package io.to.app.domain;

import io.to.kernel.Entity;

public class Member implements Entity<MemberId> {
    private final MemberId id;
    private final String lastname;
    private final String firstname;
    private final String email;
    private final String password;
    private final boolean isSubscriber;

    public Member(MemberId id, String lastname, String firstname, String email, String password, boolean isSubscriber) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.isSubscriber = isSubscriber;
    }

    public MemberId getId() {
        return id;
    }

    @Override
    public MemberId id() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSubscriber() {
        return isSubscriber;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isSubscriber=" + isSubscriber +
                '}';
    }
}
