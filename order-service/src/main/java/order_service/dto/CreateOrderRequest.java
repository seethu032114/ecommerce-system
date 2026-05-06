package order_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateOrderRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

    @Min(1)
    private int quantity;

    @Min(1)
    private double totalPrice;

    public CreateOrderRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

