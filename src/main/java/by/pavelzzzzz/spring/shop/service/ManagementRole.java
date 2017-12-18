package by.pavelzzzzz.spring.shop.service;


import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.service.model.Role;

import java.util.Set;

public interface ManagementRole {

    int addRole(String roleName) throws ServiceException;

    Set<Role> getAllRoles();

    Role findRoleById(Long roleId) throws ServiceException;

    Role findRoleByName(String roleName) throws ServiceException;

    int updateRole(Long roleId, String roleName) throws  ServiceException;

    int deleteRole(Long roleId) throws ServiceException;
}
