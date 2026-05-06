package cart_service.controller;

import cart_service.dto.CartRequest;
import cart_service.entity.Cart;
import cart_service.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addItemToCart(@RequestBody CartRequest request){
        Cart item = new Cart();
        item.setUserId(request.getUserId());
        item.setProductId(request.getProductId());
        item.setQuantity(request.getQuantity());
        System.out.println("Controller received userId = " +item.getUserId());
        return cartService.addItemToCart(item);
    }

    @GetMapping("/user/{userId}")
    public List<Cart> getCartByUserId(@PathVariable ("userId") Long userId){
        return cartService.getCartByUserId(userId);
    }

    @DeleteMapping("/{cartItemId}")
    public void removeItem(@PathVariable("cartItemId") Long cartItemId){
        cartService.removeItem(cartItemId);
    }

    @DeleteMapping("/delete/{userId}")
    public void clearCart(@PathVariable("userId") Long userId){
        cartService.clearCart(userId);
    }


}
