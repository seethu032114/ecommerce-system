package inventory_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<String> handleStockException(InsufficientStockException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
