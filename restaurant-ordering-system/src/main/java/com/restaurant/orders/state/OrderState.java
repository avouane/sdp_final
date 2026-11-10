package com.restaurant.orders.state;

import com.restaurant.orders.model.Order;

public interface OrderState {
    String getName();
    void next(Order order);
}
