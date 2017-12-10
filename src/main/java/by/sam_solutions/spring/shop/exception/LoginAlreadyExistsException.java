package by.sam_solutions.spring.shop.exception;

public class LoginAlreadyExistsException extends Exception {

    public LoginAlreadyExistsException(String message) {
        super(message);
    }
}
