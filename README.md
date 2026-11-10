# 🍽️ Restaurant Ordering System

A comprehensive Java application built to demonstrate Object-Oriented Design Patterns and Clean Code principles in an e-commerce food ordering workflow.

---

## 📌 Project Overview

The **Restaurant Ordering System** handles end-to-end restaurant management operations:
* Dynamic menu creation.
* Runtime item customization (extra toppings and size upgrades).
* Automated bill pricing (standard and promotional discounts).
* Single-entry facade operations for cart and checkout management.
* Strict state transitions for order execution.
* Real-time notifications dispatched to kitchen displays and customer devices.

---

## 🚀 Design Patterns Implemented

The architecture integrates **6 Design Patterns** decoupled into distinct Java packages:

| Pattern | Category | Java Package | Key Responsibilities |
|---|---|---|---|
| **Factory Method** | Creational | `com.restaurant.catalog.factory` | Encapsulates instantiation of menu items into categorized types (`Food`, `Drink`, `Dessert`). |
| **Decorator** | Structural | `com.restaurant.cart.decorator` | Dynamically wraps `BaseOrderItem` to append extra toppings and size surcharges at runtime. |
| **Facade** | Structural | `com.restaurant.orders.facade` | Provides a unified entry point (`RestaurantOrderFacade`) coordinating cart, pricing strategies, and order commands. |
| **Command** | Behavioral | `com.restaurant.orders.command` | Encapsulates order actions (`PlaceOrderCommand`, `CancelOrderCommand`) into executable command objects. |
| **State** | Behavioral | `com.restaurant.orders.state` | Enforces valid status transitions (`Pending` ➔ `Preparing` ➔ `Ready` ➔ `Completed` / `Cancelled`). |
| **Observer** | Behavioral | `com.restaurant.notifications.observer` | Automatically notifies subscribed listeners (`KitchenDisplay`, `CustomerNotifier`) upon state changes. |
| **Strategy** | Behavioral | `com.restaurant.pricing.strategy` | Encapsulates pricing calculation algorithms (`StandardPricing`, `DiscountPricing`). |

---

## 🏗️ Clean Code & Architecture Principles

- **Single Responsibility Principle (SRP):** Classes maintain isolated responsibilities (e.g., `Cart` manages collection storage, `Order` manages lifecycle state).
- **Open/Closed Principle (OCP):** New toppings or pricing rules can be introduced via decorators or strategies without modifying existing core logic.
- **Liskov Substitution Principle (LSP):** Decorated `OrderItem` instances can replace base menu items transparently.
- **Dependency Inversion Principle (DIP):** High-level components depend on interfaces (`PricingStrategy`, `OrderState`, `OrderObserver`).

---

## 📁 Repository Structure

```text
restaurant-ordering-system/
├── src/
│   ├── main/java/com/restaurant/
│   │   ├── app/
│   │   │   └── RestaurantOrderingApplication.java   # Application entry point
│   │   ├── cart/
│   │   │   ├── decorator/                           # Decorator Pattern
│   │   │   └── model/                               # Cart Domain Model
│   │   ├── catalog/
│   │   │   ├── factory/                             # Factory Method Pattern
│   │   │   └── model/                               # MenuItem Domain Model
│   │   ├── notifications/
│   │   │   └── observer/                            # Observer Pattern
│   │   ├── orders/
│   │   │   ├── command/                             # Command Pattern
│   │   │   ├── facade/                              # Facade Pattern
│   │   │   ├── model/                               # Order Domain Model
│   │   │   └── state/                               # State Pattern
│   │   └── pricing/
│   │       └── strategy/                            # Strategy Pattern
│   └── test/java/com/restaurant/                    # Unit Test Suites (JUnit 5)
