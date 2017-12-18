package by.pavelzzzzz.spring.shop.controllers.api;

import by.pavelzzzzz.spring.shop.exception.AuthorizationException;
import by.pavelzzzzz.spring.shop.exception.LoginAlreadyExistsException;
import by.pavelzzzzz.spring.shop.exception.LoginIsNotCorrectException;
import by.pavelzzzzz.spring.shop.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ServiceException")
    @ExceptionHandler(ServiceException.class)
    public void handleServiceException(ServiceException e){
        LOGGER.error("LoginIsNotCorrectException   " + e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Login is not correct exception")
    @ExceptionHandler(LoginIsNotCorrectException.class)
    public void handleLoginIsNotCorrectException(LoginIsNotCorrectException e){
        LOGGER.error("LoginIsNotCorrectException   " + e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Authorization exception")
    @ExceptionHandler(AuthorizationException.class)
    public void handleAuthorizationException(AuthorizationException e){
        LOGGER.error("AuthorizationException   " + e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "This login already exists")
    @ExceptionHandler(LoginAlreadyExistsException.class)
    public void handleLoginAlreadyExists (LoginAlreadyExistsException e){
        LOGGER.error("LoginAlreadyExistsException" + e.getMessage());
    }
}
