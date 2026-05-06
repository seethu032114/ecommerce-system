package order_service.entity;

public enum OrderStatus {
    CREATED,
    INVENTORY_RESERVED,
    PAYMENT_COMPLETED,
    COMPLETED,
    CANCELLED
}
