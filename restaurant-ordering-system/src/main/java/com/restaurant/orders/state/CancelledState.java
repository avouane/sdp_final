package com.restaurant.orders.state;

import com.restaurant.orders.model.Order;

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
