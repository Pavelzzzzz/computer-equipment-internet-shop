package by.sam_solutions.spring.shop.service;

import by.sam_solutions.spring.shop.exception.ServiceException;
import by.sam_solutions.spring.shop.service.model.Role;

import java.util.Set;

public interface ManagementRoleForUser {

    int addRole(Long userId, Long roleId) throws ServiceException;

    Set<Role> getRolesByUserId (Long userId) throws ServiceException;

    int hasRoleIdByUserId (Long userId, Long roleId) throws ServiceException;

    int deleteRole(Long userId, Long roleId) throws ServiceException;
}
