package com.restaurant.orders;

import com.restaurant.cart.decorator.BaseOrderItem;
import com.restaurant.cart.model.Cart;
import com.restaurant.orders.command.PlaceOrderCommand;
import com.restaurant.orders.model.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOrderCommandTest {

    @Test
    void testPlaceOrderCommandExecution() {
        Cart cart = new Cart();
        cart.addItem(new BaseOrderItem("Item", "Desc", new BigDecimal("10.00")));
        AtomicReference<Order> placedRef = new AtomicReference<>();

        PlaceOrderCommand command = new PlaceOrderCommand(
            cart,
            c -> Order.fromCart(1, c, c.getSubtotal()),
            placedRef::set
        );

        command.execute();

        assertNotNull(placedRef.get());
        assertEquals(1, placedRef.get().getId());
        assertThrows(IllegalStateException.class, command::execute);
    }

    @Test
    void testEmptyCartPlacementFails() {
        Cart emptyCart = new Cart();
        PlaceOrderCommand command = new PlaceOrderCommand(
            emptyCart,
            c -> Order.fromCart(1, c, BigDecimal.ZERO),
            o -> {}
        );

        assertThrows(IllegalStateException.class, command::execute);
    }
}