package com.restaurant.orders;

import com.restaurant.orders.model.Order;
import com.restaurant.orders.state.PreparingState;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderStateTest {

    @Test
    void testOrderStateLifecycle() {
        Order order = new Order(1, List.of(), BigDecimal.ZERO);
        assertEquals("Pending", order.getStatus());

        order.nextState();
        assertEquals("Preparing", order.getStatus());

        order.nextState();
        assertEquals("Ready", order.getStatus());

        order.nextState();
        assertEquals("Completed", order.getStatus());

        assertThrows(IllegalStateException.class, () -> order.setState(new PreparingState()));
    }
}