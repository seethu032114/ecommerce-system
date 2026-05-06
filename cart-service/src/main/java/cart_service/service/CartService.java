package cart_service.service;

import cart_service.client.ProductClient;
import cart_service.client.UserClient;
import cart_service.dto.ProductDto;
import cart_service.dto.UserDto;
import cart_service.entity.Cart;
import cart_service.exception.CartItemNotFoundException;
import cart_service.repository.CartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserClient userClient;
    private final ProductClient productClient;

    public CartService(CartRepository cartRepository, UserClient userClient, ProductClient productClient) {
        this.cartRepository = cartRepository;
        this.userClient = userClient;
        this.productClient = productClient;
    }

    public Cart addItemToCart(Cart item) {
        UserDto user = getUser(item.getUserId());
        ProductDto product = getProduct(item.getProductId());
        return cartRepository.save(item);
    }

    @CircuitBreaker(name = "userServiceCB", fallbackMethod = "userFallback")
    @Retry(name = "userServiceRetry")
    public UserDto getUser(Long userId){
        System.out.println("Calling Feign userClient with id = " +userId);
        return userClient.getUserById(userId);
    }

    @CircuitBreaker(name= "productServiceCB", fallbackMethod = "productFallback")
    @Retry(name = "productServiceRetry")
    public ProductDto getProduct(Long productId){
        return productClient.getProductById(productId);
    }

    //FALLBACKS

    public UserDto userFallback(Long userId, Throwable ex){
        throw new RuntimeException("User Service is unavailable");
    }

    public ProductDto productFallback(Long productId, Throwable ex){
        throw new RuntimeException("Product Service is unavailable");
    }

    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void removeItem(Long cartItemId) {
        if(!cartRepository.existsById(cartItemId)){
            throw new CartItemNotFoundException(cartItemId);
        }
        cartRepository.deleteById(cartItemId);
    }

    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}
