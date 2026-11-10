package com.restaurant.orders;

import com.restaurant.cart.decorator.BaseOrderItem;
import com.restaurant.cart.model.Cart;
import com.restaurant.orders.facade.RestaurantOrderFacade;
import com.restaurant.orders.model.Order;
import com.restaurant.pricing.strategy.DiscountPricing;
import com.restaurant.pricing.strategy.StandardPricing;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantOrderFacadeTest {

    @Test
    void testFacadeFlow() {
        Cart cart = new Cart();
        RestaurantOrderFacade facade = new RestaurantOrderFacade(cart, new StandardPricing());

        facade.addItem(new BaseOrderItem("Item", "Desc", new BigDecimal("20.00")));
        assertEquals(new BigDecimal("20.0"), facade.getTotal());

        facade.setPricingStrategy(new DiscountPricing(10.0));
        assertEquals(new BigDecimal("18.0"), facade.getTotal());

        AtomicReference<Order> orderRef = new AtomicReference<>();
        facade.placeOrder((c, total) -> Order.fromCart(1, c, total), orderRef::set);

        assertNotNull(orderRef.get());
        assertEquals(new BigDecimal("18.0"), orderRef.get().getTotal());
    }
}