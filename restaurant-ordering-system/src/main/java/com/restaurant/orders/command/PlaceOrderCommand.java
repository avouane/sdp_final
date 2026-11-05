package com.restaurant.orders.command;

import com.restaurant.cart.model.Cart;
import com.restaurant.orders.model.Order;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/** Creates an Order from a non-empty cart and hands it to the order workflow. */
public final class PlaceOrderCommand implements OrderCommand {
    private final Cart cart;
    private final Function<Cart, Order> orderFactory;
    private final Consumer<Order> onOrderPlaced;
    private boolean executed;

    public PlaceOrderCommand(
            Cart cart,
            Function<Cart, Order> orderFactory,
            Consumer<Order> onOrderPlaced) {
        this.cart = Objects.requireNonNull(cart, "cart");
        this.orderFactory = Objects.requireNonNull(orderFactory, "orderFactory");
        this.onOrderPlaced = Objects.requireNonNull(onOrderPlaced, "onOrderPlaced");
    }

    @Override
    public void execute() {
        if (executed) {
            throw new IllegalStateException("This place-order command has already been executed");
        }
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cannot place an order with an empty cart");
        }

        Order order = Objects.requireNonNull(orderFactory.apply(cart), "orderFactory returned null");
        onOrderPlaced.accept(order);
        executed = true;
    }
}
