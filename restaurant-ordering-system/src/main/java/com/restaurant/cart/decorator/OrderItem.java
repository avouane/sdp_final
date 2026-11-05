package com.restaurant.cart.decorator;

import java.math.BigDecimal;

/** A menu item after any customer-selected customizations have been applied. */
public interface OrderItem {
    String getName();

    String getDescription();

    BigDecimal getPrice();
}
