package com.restaurant.orders.state;

import com.restaurant.orders.model.Order;

public class ReadyState implements OrderState {
    @Override
    public String getName() {
        return "Ready";
    }

    @Override
    public void next(Order order) {
        order.setState(new CompletedState());
    }
}
