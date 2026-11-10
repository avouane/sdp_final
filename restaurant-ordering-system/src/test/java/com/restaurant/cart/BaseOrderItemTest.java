package com.restaurant.cart;

import com.restaurant.cart.decorator.BaseOrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BaseOrderItemTest {

    @Test
    void testValidBaseOrderItem() {
        BaseOrderItem item = new BaseOrderItem("Burger", "Beef burger", new BigDecimal("10.00"));
        assertEquals("Burger", item.getName());
        assertEquals("Beef burger", item.getDescription());
        assertEquals(new BigDecimal("10.00"), item.getPrice());
    }

    @Test
    void testInvalidBaseOrderItem() {
        assertThrows(IllegalArgumentException.class, () -> 
            new BaseOrderItem("", "Desc", new BigDecimal("10.00")));
        assertThrows(IllegalArgumentException.class, () -> 
            new BaseOrderItem("Burger", "Desc", new BigDecimal("-5.00")));
    }
}