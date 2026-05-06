package order_service.service;

import order_service.client.InventoryClient;
import order_service.client.PaymentClient;
import order_service.entity.Order;
import order_service.entity.OrderStatus;
import order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderSagaService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;

    public OrderSagaService(OrderRepository orderRepository,
                            InventoryClient inventoryClient,
                            PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.paymentClient = paymentClient;
    }

    @Transactional
    public Order createOrderSaga(Order order) {
        try {

            // step 1 Save Order
            order.setStatus(OrderStatus.CREATED);
            order = orderRepository.save(order);

            //step 2 Reserve Inventory
            inventoryClient.reserveStock(order.getProductId(), order.getQuantity());
            order.setStatus(OrderStatus.INVENTORY_RESERVED);
            orderRepository.save(order);

            //step3 Take payment
            paymentClient.charge(order.getId(), order.getTotalPrice());
            order.setStatus(OrderStatus.PAYMENT_COMPLETED);
            orderRepository.save(order);

            //step 4 Complete Order
            order.setStatus(OrderStatus.COMPLETED);
            return orderRepository.save(order);
        } catch (Exception ex) {
            compensate(order);
            throw ex;
        }
    }

    //Compensation Logic (rollback)
    private void compensate(Order order) {
        if (order.getStatus() == OrderStatus.PAYMENT_COMPLETED) {
            paymentClient.refund(order.getId());
        }

        if (order.getStatus() == OrderStatus.INVENTORY_RESERVED
                || order.getStatus() == OrderStatus.PAYMENT_COMPLETED) {
            inventoryClient.releaseStock(order.getProductId(), order.getQuantity());
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
