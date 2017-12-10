package by.sam_solutions.spring.shop.service.impl;

import by.sam_solutions.spring.shop.exception.ServiceException;
import by.sam_solutions.spring.shop.jdbc.dao.RoleDao;
import by.sam_solutions.spring.shop.jdbc.model.RoleTbl;
import by.sam_solutions.spring.shop.service.ManagementRole;
import by.sam_solutions.spring.shop.service.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ManagementRoleImpl implements ManagementRole {

    @Autowired
    private RoleDao roleDao;

    @Override
    public int addRole(String roleName) throws ServiceException {

        if (roleDao.findByName(roleName) != null){
            throw  new ServiceException("This role already exists");
        }

        RoleTbl role = new RoleTbl();
        role.setRole(roleName);

        roleDao.insert(role);

        return 1;
    }

    @Override
    public Set<Role> getAllRoles(){
        Set<Role> resultSet = new LinkedHashSet<>();
        Iterator<RoleTbl> iterator = roleDao.getAllRoles().iterator();
        while (iterator.hasNext()){
            RoleTbl currentRoleTbl = iterator.next();
            resultSet.add(new Role(
                    currentRoleTbl.getRoleId(),
                    currentRoleTbl.getRole()));
        }
        return resultSet;
    }

    @Override
    public Role findRoleById(Long roleId) throws ServiceException {

        RoleTbl foundRole = roleDao.findById(roleId);

        if (foundRole == null){
            throw new ServiceException("This role is not found");
        }

        Role role = new Role(foundRole.getRoleId(),
                foundRole.getRole());

        return role;
    }

    @Override
    public Role findRoleByName(String roleName) throws ServiceException{
        RoleTbl foundRole = roleDao.findByName(roleName);

        if (foundRole == null){
            throw new ServiceException("This role is not found");
        }

        Role role = new Role(foundRole.getRoleId(),
                foundRole.getRole());

        return role;
    }

    @Override
    public int updateRole(Long roleId, String roleName) throws ServiceException {
        RoleTbl foundRole = roleDao.findById(roleId);

        if (foundRole == null){
            throw new ServiceException("This role is not found");
        }

        if (roleDao.findByName(roleName) != null){
            throw  new ServiceException("This role already exists");
        }

        foundRole.setRole(roleName);

        roleDao.update(foundRole);

        return 1;
    }

    @Override
    public int deleteRole(Long roleId) throws ServiceException{
        RoleTbl foundRole = roleDao.findById(roleId);

        if( foundRole == null){
            throw new ServiceException("This role is not found");
        }

        roleDao.delete(foundRole);

        return 1;
    }
}
