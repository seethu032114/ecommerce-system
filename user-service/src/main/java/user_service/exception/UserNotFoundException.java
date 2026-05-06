package user_service.exception;

public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUId = 1L;

    public UserNotFoundException(Long userId){
        super("User Not Found With id: " +userId);
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
