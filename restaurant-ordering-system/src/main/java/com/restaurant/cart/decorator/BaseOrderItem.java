package com.restaurant.cart.decorator;

import java.math.BigDecimal;
import java.util.Objects;

/** The undecorated item selected from the restaurant menu. */
public final class BaseOrderItem implements OrderItem {
    private final String name;
    private final String description;
    private final BigDecimal price;

    public BaseOrderItem(String name, String description, BigDecimal price) {
        this.name = requireText(name, "name");
        this.description = Objects.requireNonNull(description, "description");
        this.price = requirePrice(price);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    static String requireText(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName);
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value;
    }

    static BigDecimal requirePrice(BigDecimal value) {
        Objects.requireNonNull(value, "price");
        if (value.signum() < 0) {
            throw new IllegalArgumentException("price must not be negative");
        }
        return value;
    }
}
