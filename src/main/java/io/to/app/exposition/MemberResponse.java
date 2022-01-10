package io.to.app.exposition;

import javax.validation.constraints.NotBlank;

public class MemberResponse {
    public String id;
    public String lastname;
    public String firstname;
    public String email;
    public boolean isSubscriber;

    public MemberResponse(String id, String lastname, String firstname, String email, boolean isSubscriber) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.isSubscriber = isSubscriber;
    }

    @Override
    public String toString() {
        return "MemberResponse{" +
                "id='" + id + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", isSubscriber=" + isSubscriber +
                '}';
    }
}
