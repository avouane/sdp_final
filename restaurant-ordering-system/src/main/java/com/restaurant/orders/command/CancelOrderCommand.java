package com.restaurant.orders.command;

import com.restaurant.orders.model.Order;

import java.util.Objects;
import java.util.function.Consumer;

public final class CancelOrderCommand implements OrderCommand {
    private final Order order;
    private final Consumer<Order> cancellationAction;
    private boolean executed;

    public CancelOrderCommand(Order order, Consumer<Order> cancellationAction) {
        this.order = Objects.requireNonNull(order, "order");
        this.cancellationAction = Objects.requireNonNull(cancellationAction, "cancellationAction");
    }

    @Override
    public void execute() {
        if (executed) {
            throw new IllegalStateException("This cancel-order command has already been executed");
        }
        cancellationAction.accept(order);
        executed = true;
    }
}
