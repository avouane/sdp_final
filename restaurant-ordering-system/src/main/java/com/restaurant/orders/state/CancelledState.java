package com.restaurant.orders.state;

import com.restaurant.orders.model.Order;

/** Terminal state for an order cancelled before preparation. */
public class CancelledState implements OrderState {
    @Override
    public String getName() {
        return "Cancelled";
    }

    @Override
    public void next(Order order) {
        System.out.println("Order #" + order.getId() + " was cancelled and cannot advance.");
    }
}
