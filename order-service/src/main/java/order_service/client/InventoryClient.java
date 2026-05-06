package order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @PostMapping("/inventory/reserve")
    void reserveStock(@RequestParam("productId") Long productId,
                      @RequestParam("quantity") int quantity);

    @PostMapping("/inventory/release")
    void releaseStock(@RequestParam("productId") Long productId,
                      @RequestParam("quantity") int quantity);
}
