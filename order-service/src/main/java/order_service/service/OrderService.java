package order_service.service;

import order_service.entity.Order;
import order_service.entity.OrderStatus;
import order_service.exception.OrderNotFoundException;
import order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService{

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order){
        order.setStatus(OrderStatus.CREATED);
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public void updateOrderStatus(Long orderId, String status){
        Order order = getOrderById(orderId);
        order.setStatus(OrderStatus.valueOf(status));
        orderRepository.save(order);
    }
}
