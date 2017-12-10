package by.sam_solutions.spring.shop.service;

import by.sam_solutions.spring.shop.exception.ServiceException;
import by.sam_solutions.spring.shop.service.model.User;

import java.util.Set;

    public interface ManagementUser {

        Set<User> getAllUsers();

        int addUser(String login, String email) throws ServiceException;

        User findUserById (Long userId) throws ServiceException;

        User findUserByLogin (String login) throws ServiceException;

        int updateUser(User user) throws ServiceException;

        int deleteUserById(Long userId) throws ServiceException;
}
