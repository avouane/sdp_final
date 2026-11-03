# Студент 1 — меню и цены

**Паттерны:** Factory и Strategy.

## Ваши файлы

- `src/main/java/com/restaurant/catalog/model/MenuItem.java` — модель позиции меню.
- `src/main/java/com/restaurant/catalog/factory/MenuItemFactory.java` — создание разных типов позиций меню (Factory).
- `src/main/java/com/restaurant/pricing/strategy/PricingStrategy.java` — контракт расчёта стоимости (Strategy).
- `src/main/java/com/restaurant/pricing/strategy/StandardPricing.java` — обычная цена.
- `src/main/java/com/restaurant/pricing/strategy/DiscountPricing.java` — альтернативный расчёт цены.

## Интеграция

Передайте команде согласованный формат позиции меню и метод расчёта цены. Student 2 использует позиции в корзине; Student 3 получает итог заказа для статусных уведомлений.
