package com.restaurant.notifications;

import com.restaurant.notifications.observer.OrderObserver;
import com.restaurant.orders.model.Order;
import com.restaurant.orders.state.PreparingState;
import com.restaurant.orders.state.ReadyState;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderObserverTest {

    @Test
    void testObserverNotifications() {
        Order order = new Order(100, List.of(), BigDecimal.ZERO);
        List<String> notifications = new ArrayList<>();

        OrderObserver observer = updatedOrder -> 
            notifications.add(updatedOrder.getStatus());

        order.addObserver(observer);
        order.setState(new PreparingState());
        order.setState(new ReadyState());

        assertEquals(2, notifications.size());
        assertEquals("Preparing", notifications.get(0));
        assertEquals("Ready", notifications.get(1));
    }
}