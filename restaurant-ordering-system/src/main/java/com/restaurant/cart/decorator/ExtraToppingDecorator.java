package com.restaurant.cart.decorator;

import java.math.BigDecimal;

/** Adds one paid or free topping without changing the wrapped item. */
public final class ExtraToppingDecorator extends OrderItemDecorator {
    private final String toppingName;
    private final BigDecimal toppingPrice;

    public ExtraToppingDecorator(OrderItem item, String toppingName, BigDecimal toppingPrice) {
        super(item);
        this.toppingName = BaseOrderItem.requireText(toppingName, "toppingName");
        this.toppingPrice = BaseOrderItem.requirePrice(toppingPrice);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", extra " + toppingName;
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(toppingPrice);
    }
}
