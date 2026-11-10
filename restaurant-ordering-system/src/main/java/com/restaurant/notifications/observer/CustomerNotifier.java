package com.restaurant.notifications.observer;

import com.restaurant.orders.model.Order;

public class CustomerNotifier implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("[Customer] Your order #" + order.getId()
                + " is now " + order.getStatus() + ".");
    }
}
