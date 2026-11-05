package com.restaurant.orders.command;

/** A user operation represented as an object so it can be invoked uniformly. */
public interface OrderCommand {
    void execute();
}
