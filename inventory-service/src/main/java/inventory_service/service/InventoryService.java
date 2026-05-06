package inventory_service.service;

import inventory_service.entity.Inventory;
import inventory_service.exception.InsufficientStockException;
import inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void reserveStock(Long productId, int quantity) {
        Inventory inventory= inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if(inventory.getQuantity() < quantity){
            throw new InsufficientStockException("Not enough stock available");
        }
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);
    }

    public void releaseStock(Long productId, int quantity) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        inventory.setQuantity(inventory.getQuantity() + quantity);
        inventoryRepository.save(inventory);
    }

    public int getAvailableStock(Long productId) {
         return inventoryRepository.findByProductId(productId)
                 .map(Inventory::getQuantity)
                 .orElse(0);
    }
}
