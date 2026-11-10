package com.restaurant.app;

import com.restaurant.cart.decorator.BaseOrderItem;
import com.restaurant.cart.decorator.ExtraToppingDecorator;
import com.restaurant.cart.decorator.OrderItem;
import com.restaurant.cart.decorator.SizeUpgradeDecorator;
import com.restaurant.cart.model.Cart;
import com.restaurant.catalog.factory.MenuItemFactory;
import com.restaurant.catalog.model.MenuItem;
import com.restaurant.notifications.observer.CustomerNotifier;
import com.restaurant.notifications.observer.KitchenDisplay;
import com.restaurant.orders.facade.RestaurantOrderFacade;
import com.restaurant.orders.model.Order;
import com.restaurant.pricing.strategy.DiscountPricing;
import com.restaurant.pricing.strategy.StandardPricing;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/** One end-to-end demonstration of all six design patterns. */
public class RestaurantOrderingApplication {
    public static void main(String[] args) {
        AtomicInteger nextId = new AtomicInteger(101);
        AtomicReference<Order> latestOrder = new AtomicReference<>();
        KitchenDisplay kitchen = new KitchenDisplay();
        CustomerNotifier customer = new CustomerNotifier();

        Cart cart = new Cart();
        RestaurantOrderFacade restaurant = new RestaurantOrderFacade(cart, new StandardPricing());

        // Factory: create the menu items.
        MenuItem pizza = MenuItemFactory.createMenuItem("F1", "food", "Pizza", 12.00);
        MenuItem juice = MenuItemFactory.createMenuItem("D1", "drink", "Juice", 4.00);

        // Decorator: customize a menu item without changing the base item.
        OrderItem pizzaItem = new BaseOrderItem(
                pizza.getName(), "Classic pizza", BigDecimal.valueOf(pizza.getBasePrice()));
        pizzaItem = new SizeUpgradeDecorator(pizzaItem, "Large", new BigDecimal("3.00"));
        pizzaItem = new ExtraToppingDecorator(pizzaItem, "cheese", new BigDecimal("2.00"));
        OrderItem juiceItem = new BaseOrderItem(
                juice.getName(), "Fresh juice", BigDecimal.valueOf(juice.getBasePrice()));

        restaurant.addItem(pizzaItem);
        restaurant.addItem(juiceItem);
        System.out.println("=== RESTAURANT ORDERING SYSTEM ===");
        for (OrderItem item : cart.getItems()) {
            System.out.println(item.getName() + " (" + item.getDescription() + "): $" + item.getPrice());
        }
        System.out.println("Subtotal: $" + restaurant.getSubtotal());

        // Strategy: apply the discount through the Facade.
        restaurant.setPricingStrategy(new DiscountPricing(10));
        System.out.println("Total with 10% discount: $" + restaurant.getTotal());

        // Facade + Command: turn the cart into a real order.
        restaurant.placeOrder(
                (currentCart, total) -> Order.fromCart(nextId.getAndIncrement(), currentCart, total),
                order -> {
                    latestOrder.set(order);
                    // Observer: subscribe the kitchen and the customer.
                    order.addObserver(kitchen);
                    order.addObserver(customer);
                    System.out.println("Placed order #" + order.getId() + " (" + order.getStatus() + ")");
                    System.out.println("Saved items: " + order.getItems().size() + ", total: $" + order.getTotal());
                }
        );

        Order order = latestOrder.get();
        if (order == null) {
            throw new IllegalStateException("Order was not created");
        }
        restaurant.clearCart(); // The order keeps its own copy of the cart items.

        // State: each successful transition notifies both observers.
        order.nextState(); // Pending -> Preparing
        order.nextState(); // Preparing -> Ready
        order.nextState(); // Ready -> Completed
        order.nextState(); // Already completed; no change or notification.
        System.out.println("Final order #" + order.getId() + ": " + order.getStatus()
                + ", items: " + order.getItems().size() + ", total: $" + order.getTotal());

        // Also demonstrate the CancelOrderCommand with a second pending order.
        restaurant.addItem(juiceItem);
        restaurant.setPricingStrategy(new StandardPricing());
        restaurant.placeOrder(
                (currentCart, total) -> Order.fromCart(nextId.getAndIncrement(), currentCart, total),
                cancelledOrder -> {
                    cancelledOrder.addObserver(kitchen);
                    cancelledOrder.addObserver(customer);
                    System.out.println("Placed order #" + cancelledOrder.getId() + " for cancellation demo");
                    restaurant.cancelOrder(cancelledOrder, Order::cancel);
                    System.out.println("Order #" + cancelledOrder.getId() + " status: " + cancelledOrder.getStatus());
                }
        );
    }
}
