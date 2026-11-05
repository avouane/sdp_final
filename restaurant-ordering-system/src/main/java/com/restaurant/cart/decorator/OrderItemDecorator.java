package com.restaurant.cart.decorator;

import java.math.BigDecimal;
import java.util.Objects;

/** Base class that forwards the OrderItem contract to the wrapped item. */
public abstract class OrderItemDecorator implements OrderItem {
    private final OrderItem item;

    protected OrderItemDecorator(OrderItem item) {
        this.item = Objects.requireNonNull(item, "item");
    }

    protected final OrderItem getWrappedItem() {
        return item;
    }

    @Override
    public String getName() {
        return item.getName();
    }

    @Override
    public String getDescription() {
        return item.getDescription();
    }

    @Override
    public BigDecimal getPrice() {
        return item.getPrice();
    }
}
