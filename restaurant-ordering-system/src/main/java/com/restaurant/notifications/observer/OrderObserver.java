package com.restaurant.notifications.observer;

import com.restaurant.orders.model.Order;

public interface OrderObserver {
    void update(Order order);
}
