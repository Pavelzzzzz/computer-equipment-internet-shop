package by.pavelzzzzz.spring.shop.service;

import by.pavelzzzzz.spring.shop.exception.ServiceException;

public interface ManagementPasswordForUser {

    int setPassword(Long userId, String inputPassword) throws ServiceException;

    int updatePassword(Long userId, String inputPassword) throws ServiceException;

    int passwordVerification(Long userId, String password) throws ServiceException;

    int deletePassword(Long userId) throws ServiceException;
}
