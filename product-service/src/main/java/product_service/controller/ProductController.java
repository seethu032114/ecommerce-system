package product_service.controller;

import org.springframework.web.bind.annotation.*;
import product_service.entity.Product;
import product_service.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
}
