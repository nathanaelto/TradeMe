package io.to.kernel;

public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}
