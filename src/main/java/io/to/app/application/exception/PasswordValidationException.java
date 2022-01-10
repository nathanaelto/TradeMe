package io.to.app.application.exception;

public class PasswordValidationException extends MemberException {
    public PasswordValidationException() {
        super("Password format invalid");
    }
}
