# Студент 2 — корзина и команды заказа

**Паттерны:** Decorator и Command.

## Ваши файлы

- `src/main/java/com/restaurant/cart/model/Cart.java` — состав и итог корзины.
- `src/main/java/com/restaurant/cart/decorator/OrderItem.java` — общий контракт элемента заказа.
- `src/main/java/com/restaurant/cart/decorator/BaseOrderItem.java` — базовый элемент.
- `src/main/java/com/restaurant/cart/decorator/ExtraToppingDecorator.java` — добавка к блюду (Decorator).
- `src/main/java/com/restaurant/cart/decorator/SizeUpgradeDecorator.java` — изменение размера (Decorator).
- `src/main/java/com/restaurant/orders/command/OrderCommand.java` — контракт команды.
- `src/main/java/com/restaurant/orders/command/PlaceOrderCommand.java` — оформление заказа (Command).
- `src/main/java/com/restaurant/orders/command/CancelOrderCommand.java` — отмена заказа (Command).

## Интеграция

Используйте типы меню и цены, согласованные со Student 1. Передавайте оформленный заказ Student 3 для управления статусом.
