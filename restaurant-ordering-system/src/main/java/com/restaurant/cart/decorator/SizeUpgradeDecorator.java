package com.restaurant.cart.decorator;

import java.math.BigDecimal;

/** Adds a size label and its surcharge to the wrapped item. */
public final class SizeUpgradeDecorator extends OrderItemDecorator {
    private final String sizeName;
    private final BigDecimal surcharge;

    public SizeUpgradeDecorator(OrderItem item, String sizeName, BigDecimal surcharge) {
        super(item);
        this.sizeName = BaseOrderItem.requireText(sizeName, "sizeName");
        this.surcharge = BaseOrderItem.requirePrice(surcharge);
    }

    @Override
    public String getName() {
        return sizeName + " " + super.getName();
    }

    @Override
    public BigDecimal getPrice() {
        return super.getPrice().add(surcharge);
    }
}
