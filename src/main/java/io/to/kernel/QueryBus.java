package io.to.kernel;

public interface QueryBus {
    <Q extends Query, R> R send(Q query);
}
