package order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service", url = "http://mock-payment")
public interface PaymentClient {

    @PostMapping("/payment/charge")
    void charge(@RequestParam("orderId") Long orderId,
                @RequestParam("amount") double amount);

    @PostMapping("/payment/refund")
    void refund(@RequestParam("orderId") Long orderId);
}
