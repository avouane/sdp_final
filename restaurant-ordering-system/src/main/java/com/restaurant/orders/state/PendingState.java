package com.restaurant.orders.state;

import com.restaurant.orders.model.Order;

public class PendingState implements OrderState {
    @Override
    public String getName() {
        return "Pending";
    }

    @Override
    public void next(Order order) {
        order.setState(new PreparingState());
    }
}
