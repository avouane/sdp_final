package com.restaurant.notifications.observer;

import com.restaurant.orders.model.Order;

public class KitchenDisplay implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("[Kitchen] Order #" + order.getId()
                + " status: " + order.getStatus());
    }
}
