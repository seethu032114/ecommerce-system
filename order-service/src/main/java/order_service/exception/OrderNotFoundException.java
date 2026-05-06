package order_service.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id){
        super("Order Not found with id :" +id);
    }
}
