package com.restaurant.orders.facade;

import com.restaurant.cart.decorator.OrderItem;
import com.restaurant.cart.model.Cart;
import com.restaurant.orders.command.CancelOrderCommand;
import com.restaurant.orders.command.PlaceOrderCommand;
import com.restaurant.orders.model.Order;
import com.restaurant.pricing.strategy.PricingStrategy;


import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/** Coordinates cart pricing and order commands behind one application-facing API. */
public final class RestaurantOrderFacade {
    private final Cart cart;
    private PricingStrategy pricingStrategy;

    public RestaurantOrderFacade(Cart cart, PricingStrategy pricingStrategy) {
        this.cart = Objects.requireNonNull(cart, "cart");
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy, "pricingStrategy");
    }

    public void addItem(OrderItem item) {
        cart.addItem(item);
    }

    public boolean removeItem(OrderItem item) {
        return cart.removeItem(item);
    }

    public void clearCart() {
        cart.clear();
    }

    public BigDecimal getSubtotal() {
        return cart.getSubtotal();
    }

    /** Applies the configured Strategy to the cart subtotal. */
    public BigDecimal getTotal() {
        double pricedAmount = pricingStrategy.calculatePrice(cart.getSubtotal().doubleValue());
        if (!Double.isFinite(pricedAmount) || pricedAmount < 0) {
            throw new IllegalStateException("Pricing strategy returned an invalid amount");
        }
        return BigDecimal.valueOf(pricedAmount);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy, "pricingStrategy");
    }

    /** Prices the cart, creates an order, and executes the existing Command. */
    public void placeOrder(BiFunction<Cart, BigDecimal, Order> orderFactory, Consumer<Order> onOrderPlaced) {
        Objects.requireNonNull(orderFactory, "orderFactory");
        Objects.requireNonNull(onOrderPlaced, "onOrderPlaced");
        new PlaceOrderCommand(cart, currentCart -> orderFactory.apply(currentCart, getTotal()), onOrderPlaced)
                .execute();
    }

    /** Creates and executes the existing cancellation Command. */
    public void cancelOrder(Order order, Consumer<Order> cancellationAction) {
        new CancelOrderCommand(order, cancellationAction).execute();
    }
}
