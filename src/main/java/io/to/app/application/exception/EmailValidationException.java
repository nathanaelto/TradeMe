package io.to.app.application.exception;

public class EmailValidationException extends MemberException {
    public EmailValidationException() {
        super("Email format invalid");
    }
}
