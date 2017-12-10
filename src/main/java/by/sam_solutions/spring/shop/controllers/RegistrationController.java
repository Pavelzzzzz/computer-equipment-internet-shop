package by.sam_solutions.spring.shop.controllers;

import by.sam_solutions.spring.shop.exception.LoginAlreadyExistsException;
import by.sam_solutions.spring.shop.exception.ServiceException;
import by.sam_solutions.spring.shop.service.ManagementPasswordForUser;
import by.sam_solutions.spring.shop.service.ManagementUser;
import by.sam_solutions.spring.shop.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registration")
public class RegistrationController {

    @Autowired
    private ManagementUser managementUser;

    @Autowired
    private ManagementPasswordForUser managementPasswordForUser;

    @PostMapping()
        public User registration(String login, String email, String password) throws LoginAlreadyExistsException {
        try {
            managementUser.addUser(login, email);
            User user = managementUser.findUserByLogin(login);
            managementPasswordForUser.setPassword(user.getUserId(), password);
            return user;
        } catch (ServiceException e) {
            throw new LoginAlreadyExistsException(e.getMessage());
        }
    }
}
