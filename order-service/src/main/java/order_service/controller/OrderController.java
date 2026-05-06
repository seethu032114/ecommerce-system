package order_service.controller;

import jakarta.validation.Valid;
import order_service.dto.CreateOrderRequest;
import order_service.entity.Order;
import order_service.service.OrderSagaService;
import order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderSagaService orderSagaService;

    public OrderController(OrderService orderService, OrderSagaService orderSagaService) {
        this.orderService = orderService;
        this.orderSagaService = orderSagaService;
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody CreateOrderRequest request){
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(request.getTotalPrice());

        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long id){
        return orderService.getOrderById(id);
    }


    public Order createOrderWithSaga(@Valid @RequestBody CreateOrderRequest request){
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(request.getTotalPrice());

        return orderSagaService.createOrderSaga(order);
    }

}
