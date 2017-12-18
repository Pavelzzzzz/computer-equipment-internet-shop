package by.pavelzzzzz.spring.shop.service.impl;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.jdbc.dao.UserDao;
import by.pavelzzzzz.spring.shop.jdbc.model.UserTbl;
import by.pavelzzzzz.spring.shop.service.ManagementUser;
import by.pavelzzzzz.spring.shop.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ManagementUserImpl implements ManagementUser {

    @Autowired
    private UserDao userDao;

    @Override
    public Set<User> getAllUsers(){
        Set<User> resultSet = new LinkedHashSet<>();
        Iterator<UserTbl> iterator =
                userDao.getAllUsers().iterator();
        while (iterator.hasNext()){
            UserTbl foundUser = iterator.next();
            resultSet.add(new User(foundUser.getUserId(),
                    foundUser.getLogin(),
                    foundUser.getEmail(),
                    foundUser.isActive()));
        }
        return resultSet;


    }

    @Override
    public int addUser (String login, String email) throws ServiceException {

        if  (userDao.findByLogin(login) != null) {
            throw new ServiceException("This login already exists: " + login);
        }

        UserTbl user = new UserTbl();
        user.setLogin(login);
        user.setEmail(email);

        userDao.insert(user);

        return 1;
    }

    @Override
    public User findUserById(Long userId) throws ServiceException {
        UserTbl foundUser = userDao.findById(userId);

        if  ( foundUser == null) {
            throw new ServiceException("This user is not found");
        }

        User user = new User(foundUser.getUserId(),
                foundUser.getLogin(),
                foundUser.getEmail(),
                foundUser.isActive());

        return user;
    }

    @Override
    public User findUserByLogin(String login) throws ServiceException {
        UserTbl foundUser = userDao.findByLogin(login);

        if  (foundUser == null) {
            throw new ServiceException("This user is not found");
        }

        User user = new User(foundUser.getUserId(),
                foundUser.getLogin(),
                foundUser.getEmail(),
                foundUser.isActive());

        return user;
    }

    @Override
    public int updateUser(User user) throws ServiceException {
        UserTbl foundUser = userDao.findById(user.getUserId());

        if  ( foundUser == null) {
            throw new ServiceException("This user is not found");
        }

        if  ((userDao.findByLogin(user.getLogin()) != null) &
                (userDao.findByLogin(user.getLogin()).getUserId() != user.getUserId())) {
            throw new ServiceException("This login already exists");
        }

        foundUser.setLogin(user.getLogin());
        foundUser.setEmail(user.getEmail());
        foundUser.setActive(user.isActive());

        userDao.update(foundUser);

        return 1;
    }

    @Override
    public int deleteUserById (Long userId) throws ServiceException {
        UserTbl foundUser = userDao.findById(userId);

        if  ( foundUser == null) {
            throw new ServiceException("This user is not found");
        }

        userDao.delete(foundUser);

        return 1;
    }
}
