package com.restaurant.cart;

import com.restaurant.cart.decorator.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DecoratorTest {

    @Test
    void testDecoratorComposition() {
        OrderItem base = new BaseOrderItem("Pizza", "Margherita", new BigDecimal("10.00"));
        OrderItem topped = new ExtraToppingDecorator(base, "Cheese", new BigDecimal("1.50"));
        OrderItem resized = new SizeUpgradeDecorator(topped, "Large", new BigDecimal("3.00"));

        assertEquals("Large Pizza", resized.getName());
        assertEquals("Margherita, extra Cheese", resized.getDescription());
        assertEquals(new BigDecimal("14.50"), resized.getPrice());
    }
}