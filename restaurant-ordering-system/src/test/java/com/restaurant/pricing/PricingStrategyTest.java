package com.restaurant.pricing;

import com.restaurant.pricing.strategy.DiscountPricing;
import com.restaurant.pricing.strategy.StandardPricing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PricingStrategyTest {

    @Test
    void testStandardPricing() {
        StandardPricing standard = new StandardPricing();
        assertEquals(100.0, standard.calculatePrice(100.0), 0.001);
    }

    @Test
    void testDiscountPricing() {
        DiscountPricing discount = new DiscountPricing(15.0);
        assertEquals(85.0, discount.calculatePrice(100.0), 0.001);
    }
}