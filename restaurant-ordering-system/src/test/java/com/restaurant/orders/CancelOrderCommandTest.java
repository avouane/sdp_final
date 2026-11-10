package com.restaurant.orders;

import com.restaurant.orders.command.CancelOrderCommand;
import com.restaurant.orders.model.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class CancelOrderCommandTest {

    @Test
    void testCancelOrderCommandExecution() {
        Order order = new Order(1, List.of(), BigDecimal.ZERO);
        AtomicBoolean cancelled = new AtomicBoolean(false);

        CancelOrderCommand command = new CancelOrderCommand(order, o -> {
            o.cancel();
            cancelled.set(true);
        });

        command.execute();

        assertTrue(cancelled.get());
        assertEquals("Cancelled", order.getStatus());
        assertThrows(IllegalStateException.class, command::execute);
    }
}