# Restaurant Ordering System

A Java application demonstrating software design patterns and clean code principles for a restaurant ordering backend.

---

## Project Overview

The Restaurant Ordering System handles end-to-end restaurant management operations:
* Dynamic menu creation.
* Runtime item customization (extra toppings and size upgrades).
* Automated bill pricing (standard and promotional discounts).
* Single-entry facade operations for cart and checkout management.
* Strict state transitions for order execution.
* Real-time notifications dispatched to kitchen displays and customer devices.

---

## Design Patterns

| Pattern | Type | Package | Description |
|---|---|---|---|
| Factory Method | Creational | com.restaurant.catalog.factory | Instantiates menu items into food, drink, or dessert categories. |
| Decorator | Structural | com.restaurant.cart.decorator | Wraps base items to add extra toppings and size surcharges. |
| Facade | Structural | com.restaurant.orders.facade | Simplifies cart, pricing, and command operations behind one class. |
| Command | Behavioral | com.restaurant.orders.command | Wraps order placement and cancellation into command objects. |
| State | Behavioral | com.restaurant.orders.state | Manages status changes from pending to completed or cancelled. |
| Observer | Behavioral | com.restaurant.notifications.observer | Notifies kitchen and customer modules when an order status changes. |
| Strategy | Behavioral | com.restaurant.pricing.strategy | Calculates final order totals with standard or discount rates. |

---

## Clean Code Practices

- Single Responsibility: Each class handles one clear task.
- Open/Closed: New toppings or discounts can be added without changing existing code.
- Interface Segregation: Components depend on small, focused interfaces.
- Dependency Inversion: High-level logic relies on abstractions rather than concrete classes.

---

## Repository Structure

```text
restaurant-ordering-system/
├── src/
│   ├── main/java/com/restaurant/
│   │   │
│   │   ├── catalog/                          # [Factory Method Pattern]
│   │   │   ├── factory/MenuItemFactory.java  # Menu item creation factory
│   │   │   └── model/MenuItem.java           # Base menu item domain model
│   │   │
│   │   ├── cart/                             # [Decorator Pattern]
│   │   │   ├── decorator/
│   │   │   │   ├── OrderItem.java            # Order item interface
│   │   │   │   ├── BaseOrderItem.java        # Standard dish item
│   │   │   │   ├── OrderItemDecorator.java   # Abstract decorator
│   │   │   │   ├── ExtraToppingDecorator.java# Decorator: extra toppings
│   │   │   │   └── SizeUpgradeDecorator.java # Decorator: size upgrades
│   │   │   └── model/Cart.java               # Customer cart model
│   │   │
│   │   ├── pricing/                          # [Strategy Pattern]
│   │   │   └── strategy/
│   │   │       ├── PricingStrategy.java      # Pricing strategy contract
│   │   │       ├── StandardPricing.java      # Standard pricing calculation
│   │   │       └── DiscountPricing.java      # Promotional discount pricing
│   │   │
│   │   ├── notifications/                    # [Observer Pattern]
│   │   │   └── observer/
│   │   │       ├── OrderObserver.java        # Subscriber interface
│   │   │       ├── KitchenDisplay.java       # Kitchen subscriber
│   │   │       └── CustomerNotifier.java     # Customer subscriber
│   │   │
│   │   ├── orders/                           # [Facade, State, Command Patterns]
│   │   │   ├── facade/
│   │   │   │   └── RestaurantOrderFacade.java# [Facade] Main application interface
│   │   │   ├── command/
│   │   │   │   ├── OrderCommand.java         # [Command] Command interface
│   │   │   │   ├── PlaceOrderCommand.java    # Place order command
│   │   │   │   └── CancelOrderCommand.java   # Cancel order command
│   │   │   ├── state/
│   │   │   │   ├── OrderState.java           # [State] Order state interface
│   │   │   │   ├── PendingState.java         # Pending status
│   │   │   │   ├── PreparingState.java       # Preparing status
│   │   │   │   ├── ReadyState.java           # Ready status
│   │   │   │   ├── CompletedState.java       # Completed status
│   │   │   │   └── CancelledState.java       # Cancelled status
│   │   │   └── model/Order.java              # Order domain model (State & Observer context)
│   │   │
│   │   └── app/
│   │       └── RestaurantOrderingApplication.java # Main application entry point
│   │
│   └── test/java/com/restaurant/             # Unit Test Suites (JUnit 5)
│       ├── catalog/MenuItemFactoryTest.java
│       ├── cart/DecoratorTest.java
│       ├── pricing/PricingStrategyTest.java
│       └── orders/
│           ├── OrderStateAndObserverTest.java
│           └── RestaurantOrderFacadeTest.java
