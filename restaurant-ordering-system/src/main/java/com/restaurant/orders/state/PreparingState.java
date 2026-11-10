package com.restaurant.orders.state;

import com.restaurant.orders.model.Order;

public class PreparingState implements OrderState {
    @Override
    public String getName() {
        return "Preparing";
    }

    @Override
    public void next(Order order) {
        order.setState(new ReadyState());
    }
}
