package com.restaurant.cart.decorator;

import java.math.BigDecimal;

public interface OrderItem {
    String getName();

    String getDescription();

    BigDecimal getPrice();
}
