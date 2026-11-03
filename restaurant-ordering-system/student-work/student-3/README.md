# Студент 3 — статусы, уведомления и сборка сценария

**Паттерны:** State и Observer.

## Ваши файлы

- `src/main/java/com/restaurant/orders/model/Order.java` — заказ и текущий статус.
- `src/main/java/com/restaurant/orders/state/OrderState.java` — контракт состояния.
- `src/main/java/com/restaurant/orders/state/PendingState.java` — ожидает подтверждения.
- `src/main/java/com/restaurant/orders/state/PreparingState.java` — готовится.
- `src/main/java/com/restaurant/orders/state/ReadyState.java` — готов к выдаче.
- `src/main/java/com/restaurant/orders/state/CompletedState.java` — завершён.
- `src/main/java/com/restaurant/notifications/observer/OrderObserver.java` — контракт подписчика.
- `src/main/java/com/restaurant/notifications/observer/KitchenDisplay.java` — получатель событий для кухни.
- `src/main/java/com/restaurant/notifications/observer/CustomerNotifier.java` — получатель событий для клиента.
- `src/main/java/com/restaurant/app/RestaurantOrderingApplication.java` — точка сборки демонстрационного сценария.

## Интеграция

Принимайте готовый заказ от Student 2. Согласуйте переходы статусов и формат события с обоими участниками; уведомляйте кухню и клиента о сменах статуса.
