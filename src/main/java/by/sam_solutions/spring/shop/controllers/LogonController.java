package by.sam_solutions.spring.shop.controllers;

import by.sam_solutions.spring.shop.exception.AuthorizationException;
import by.sam_solutions.spring.shop.exception.LoginIsNotCorrectException;
import by.sam_solutions.spring.shop.exception.ServiceException;
import by.sam_solutions.spring.shop.service.ManagementPasswordForUser;
import by.sam_solutions.spring.shop.service.ManagementUser;
import by.sam_solutions.spring.shop.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logon")
public class LogonController {

    @Autowired
    private ManagementUser managementUser;

    @Autowired
    private ManagementPasswordForUser managementPasswordForUser;

    @PostMapping()
    public User logon(String login, String password) throws AuthorizationException,
                                                        LoginIsNotCorrectException {
        try {
            User user = managementUser.findUserByLogin(login);
            if (managementPasswordForUser.passwordVerification(
                    user.getUserId(), password) != 1){
                throw new AuthorizationException("Logon failed");
            }
            return user;
        } catch (ServiceException e) {
            throw new LoginIsNotCorrectException(e.getMessage());
        }
    }
}
