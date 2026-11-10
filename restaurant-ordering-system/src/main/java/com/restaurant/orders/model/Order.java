package com.restaurant.orders.model;

import com.restaurant.cart.decorator.OrderItem;
import com.restaurant.cart.model.Cart;
import com.restaurant.notifications.observer.OrderObserver;
import com.restaurant.orders.state.CancelledState;
import com.restaurant.orders.state.CompletedState;
import com.restaurant.orders.state.OrderState;
import com.restaurant.orders.state.PendingState;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private final int id;
    private final List<OrderItem> items;
    private final BigDecimal total;
    private OrderState state;
    private final List<OrderObserver> observers = new ArrayList<>();

    public Order(int id) {
        this(id, List.of(), BigDecimal.ZERO);
    }

    public Order(int id, List<OrderItem> items, BigDecimal total) {
        if (id <= 0) {
            throw new IllegalArgumentException("Order ID must be positive");
        }
        this.id = id;
        this.items = List.copyOf(Objects.requireNonNull(items, "items"));
        this.total = Objects.requireNonNull(total, "total");
        if (total.signum() < 0) {
            throw new IllegalArgumentException("Total must not be negative");
        }
        this.state = new PendingState();
    }

    public static Order fromCart(int id, Cart cart, BigDecimal total) {
        Objects.requireNonNull(cart, "cart");
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cannot create an order from an empty cart");
        }
        return new Order(id, cart.getItems(), total);
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getStatus() {
        return state.getName();
    }

    public void addObserver(OrderObserver observer) {
        OrderObserver checked = Objects.requireNonNull(observer, "observer");
        if (!observers.contains(checked)) {
            observers.add(checked);
        }
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void nextState() {
        state.next(this);
    }

    public void cancel() {
        if (!(state instanceof PendingState)) {
            throw new IllegalStateException("Only pending orders can be cancelled");
        }
        setState(new CancelledState());
    }

    public void setState(OrderState newState) {
        OrderState checked = Objects.requireNonNull(newState, "state");
        if (state instanceof CompletedState || state instanceof CancelledState) {
            throw new IllegalStateException("A finished order cannot change status");
        }
        this.state = checked;
        notifyObservers();
    }

    private void notifyObservers() {
        for (OrderObserver observer : new ArrayList<>(observers)) {
            observer.update(this);
        }
    }
}
