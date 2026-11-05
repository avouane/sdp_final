package com.restaurant.cart.model;

import com.restaurant.cart.decorator.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Mutable customer cart containing customized order items. */
public final class Cart {
    private final List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item) {
        items.add(Objects.requireNonNull(item, "item"));
    }

    public boolean removeItem(OrderItem item) {
        return items.remove(Objects.requireNonNull(item, "item"));
    }

    public void clear() {
        items.clear();
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(new ArrayList<>(items));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public BigDecimal getSubtotal() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
