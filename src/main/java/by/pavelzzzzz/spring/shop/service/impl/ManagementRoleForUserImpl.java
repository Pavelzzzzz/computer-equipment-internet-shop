package by.pavelzzzzz.spring.shop.service.impl;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.jdbc.dao.RoleDao;
import by.pavelzzzzz.spring.shop.jdbc.dao.UserDao;
import by.pavelzzzzz.spring.shop.jdbc.dao.UserRoleDao;
import by.pavelzzzzz.spring.shop.jdbc.model.RoleTbl;
import by.pavelzzzzz.spring.shop.jdbc.model.UserRoleTbl;
import by.pavelzzzzz.spring.shop.service.ManagementRoleForUser;
import by.pavelzzzzz.spring.shop.service.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ManagementRoleForUserImpl implements ManagementRoleForUser{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public Set<Role> getRolesByUserId(Long userId) throws ServiceException {
        if (userDao.findById(userId) == null){
            throw new ServiceException("This user not found");
        }
        Set<Role> resultSet = new LinkedHashSet<>();
        Iterator<UserRoleTbl> iterator =
                userRoleDao.getAllRoles(userId).iterator();
        while (iterator.hasNext()){
            RoleTbl foundRole = roleDao.findById(iterator.next().getRoleId());
            resultSet.add(new Role(foundRole.getRoleId(),
                    foundRole.getRole()));
        }
        return resultSet;
    }

    @Override
    public int addRole(Long userId, Long roleId) throws ServiceException {

        UserRoleTbl userRole = new UserRoleTbl();

        userRole.setUserId(userId);
        userRole.setRoleId(roleId);

            if  (userDao.findById(userId) == null) {
                throw new ServiceException("This user not found");
            }
            if (roleDao.findById(roleId) == null) {
                throw new ServiceException("This role not found for selected user");
            }
            if (userRoleDao.isExistObject(userRole)){
                    throw new ServiceException("This user already has this role");
            }

            userRoleDao.insert(userRole);
            return 1;
    }

    @Override
    public int hasRoleIdByUserId(Long userId, Long roleId) throws ServiceException {

        UserRoleTbl userRole = new UserRoleTbl();

        userRole.setUserId(userId);
        userRole.setRoleId(roleId);

            if  (userDao.findById(userId) == null) {
                throw new ServiceException("This user not found");
            }
            if (roleDao.findById(roleId) == null) {
                throw new ServiceException("This role not found for selected user");
            }
            if (userRoleDao.isExistObject(userRole)){
                return 1;
            }
            return 0;
    }

    @Override
    public int deleteRole(Long userId, Long roleId) throws ServiceException {

        UserRoleTbl userRole = new UserRoleTbl();

        userRole.setUserId(userId);
        userRole.setRoleId(roleId);

        if  (userDao.findById(userId) == null) {
            throw new ServiceException("This user not found");
        }
        if (roleDao.findById(roleId) == null) {
            throw new ServiceException("This role not found for selected user");
        }
        if (!userRoleDao.isExistObject(userRole)){
            throw new ServiceException("This user has not this role");
        }

        userRoleDao.delete(userRole);
        return 1;
    }
}
