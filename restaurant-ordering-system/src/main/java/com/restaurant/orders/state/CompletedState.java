package com.restaurant.orders.state;

import com.restaurant.orders.model.Order;

public class CompletedState implements OrderState {
    @Override
    public String getName() {
        return "Completed";
    }

    @Override
    public void next(Order order) {
        System.out.println("Order #" + order.getId() + " is already completed.");
    }
}
