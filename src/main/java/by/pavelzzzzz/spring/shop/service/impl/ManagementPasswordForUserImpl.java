package by.pavelzzzzz.spring.shop.service.impl;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.jdbc.dao.PasswordDao;
import by.pavelzzzzz.spring.shop.jdbc.dao.UserDao;
import by.pavelzzzzz.spring.shop.jdbc.model.PasswordTbl;
import by.pavelzzzzz.spring.shop.service.ManagementPasswordForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementPasswordForUserImpl implements ManagementPasswordForUser {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordDao passwordDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setPasswordDao(PasswordDao passwordDao) {
        this.passwordDao = passwordDao;
    }

    @Override
    public int setPassword(Long userId, String inputPassword) throws ServiceException {

        if  (userDao.findById(userId) == null) {
            throw new ServiceException("User not found");
        }
        if (passwordDao.findByUserId(userId) != null){
            throw new ServiceException("Password is already set for this user");
        }

        PasswordTbl password = new PasswordTbl();

        password.setUserId(userId);
        password.setPassword(inputPassword);

        passwordDao.insert(password);
        return 1;
    }

    @Override
    public int updatePassword(Long userId, String inputPassword) throws ServiceException {

        if  (userDao.findById(userId) == null) {
            throw new ServiceException("User not found");
        }
        if (passwordDao.findByUserId(userId) == null){
            throw new ServiceException("Password is not already set for this user");
        }

        PasswordTbl password = new PasswordTbl();

        password.setUserId(userId);
        password.setPassword(inputPassword);

        passwordDao.update(password);
        return 1;
    }

    @Override
    public int passwordVerification(Long userId, String password) throws ServiceException{

        if  (userDao.findById(userId) == null) {
            throw new ServiceException("User not found");
        }

        if (passwordDao.findByUserId(userId) == null){
            throw new ServiceException("Password is not already set for this user");
        }

        if (passwordDao.findByUserId(userId).getPassword().equals(password)){
            return 1;
        }

        return 0;
    }

    @Override
    public int deletePassword(Long userId) throws ServiceException {

        if  (userDao.findById(userId) == null) {
            throw new ServiceException("User not found");
        }

        PasswordTbl foundPassword = passwordDao.findByUserId(userId);

        if (foundPassword == null) {
            throw new ServiceException("Password is not already set for this user");
        }

        passwordDao.delete(foundPassword);
        return 1;
    }

}
