package inventory_service.controller;

import inventory_service.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/reserve")
    public void reserveStock(@RequestParam Long productId,
                             @RequestParam int quantity){
        inventoryService.reserveStock(productId, quantity);
    }


    @PostMapping("/release")
    public void releaseStock(@RequestParam Long productId,
                             @RequestParam int quantity){
        inventoryService.releaseStock(productId, quantity);
    }

    @GetMapping("/{productId}")
    public int getStock(@PathVariable("productId") Long productId){
        return inventoryService.getAvailableStock(productId);
    }
}
