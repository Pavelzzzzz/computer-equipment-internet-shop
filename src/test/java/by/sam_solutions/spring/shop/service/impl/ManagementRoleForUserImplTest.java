package by.sam_solutions.spring.shop.service.impl;

import by.sam_solutions.spring.shop.exception.ServiceException;
import by.sam_solutions.spring.shop.jdbc.dao.RoleDao;
import by.sam_solutions.spring.shop.jdbc.dao.UserDao;
import by.sam_solutions.spring.shop.jdbc.dao.UserRoleDao;
import by.sam_solutions.spring.shop.jdbc.model.RoleTbl;
import by.sam_solutions.spring.shop.jdbc.model.UserRoleTbl;
import by.sam_solutions.spring.shop.jdbc.model.UserTbl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ManagementRoleForUserImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private UserRoleDao userRoleDao;

    @InjectMocks
    private ManagementRoleForUserImpl managementRoleForUser;

    @Test
    public void getRolesByUserId (){
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(userRoleDao.getAllRoles(anyLong()))
                .thenReturn(new HashSet<UserRoleTbl>());

        try {
            assertEquals(0,
                managementRoleForUser.getRolesByUserId(1L).size());
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void getRolesByUserIdUserThisUserNotFound(){
        when(userDao.findById(anyLong())).thenReturn(null);
        try{
            managementRoleForUser.getRolesByUserId(22L);
        } catch (ServiceException e) {
            assertEquals("This user not found",
                    e.getMessage());
        }
    }

    @Test
    public void addRole() {

        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(false);
        doNothing().when(userRoleDao).insert(any(UserRoleTbl.class));

        try {
            assertEquals(1,
                    managementRoleForUser.addRole(1L, 1L));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void addRoleThisUserNotFound() {

        when(userDao.findById(anyLong())).thenReturn(null);
        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(false);

        try {
            managementRoleForUser.addRole(1L, 1L);
        } catch (ServiceException e) {
            assertEquals("This user not found",
                    e.getMessage());
        }
    }

    @Test
    public void addRoleThisRoleNotFound() {

        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(roleDao.findById(anyLong())).thenReturn(null);
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(false);

        try {
            managementRoleForUser.addRole(1L, 1L);
        } catch (ServiceException e) {
            assertEquals("This role not found for selected user",
                    e.getMessage());
        }
    }

    @Test
    public void addRoleThisUserAlreadyHasThisRole() {

        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(true);

        try {
            managementRoleForUser.addRole(1L, 1L);
        } catch (ServiceException e) {
            assertEquals("This user already has this role",
                    e.getMessage());
        }
    }

    @Test
    public void hasRole() {

        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(true);

        try {
            assertEquals(1,
                    managementRoleForUser.hasRoleIdByUserId(1L, 1L));
        } catch (ServiceException e) {
            fail();
        }

    }

    @Test
    public void hasRoleNegative() {

        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(false);

        try {
            assertEquals(0,
                    managementRoleForUser.hasRoleIdByUserId(1L, 1L));
        } catch (ServiceException e) {
            fail();
        }

    }

    @Test
    public void hasRoleThisUserNotFound() {

        when(userDao.findById(anyLong())).thenReturn(null);
        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(true);

        try {
            managementRoleForUser.hasRoleIdByUserId(1L, 1L);
        } catch (ServiceException e) {
            assertEquals("This user not found",
                    e.getMessage());
        }
    }

    @Test
    public void hasRoleThisRoleNotFound() {

        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(roleDao.findById(anyLong())).thenReturn(null);
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(true);

        try {
            managementRoleForUser.hasRoleIdByUserId(1L, 1L);
        } catch (ServiceException e) {
            assertEquals("This role not found for selected user",
                    e.getMessage());
        }
    }

    @Test
    public void deleteRole() {

        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(true);
        doNothing().when(userRoleDao).delete(any(UserRoleTbl.class));

        try {
            assertEquals(1,
                    managementRoleForUser.deleteRole(1L, 1L));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void deleteRoleThisUserNotFound() {

        when(userDao.findById(anyLong())).thenReturn(null);
        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(true);

        try {
            managementRoleForUser.deleteRole(1L, 1L);
        } catch (ServiceException e) {
            assertEquals("This user not found",
                    e.getMessage());
        }
    }

    @Test
    public void deleteRoleThisRoleNotFound() {

        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(roleDao.findById(anyLong())).thenReturn(null);
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(true);

        try {
            managementRoleForUser.deleteRole(1L, 1L);
        } catch (ServiceException e) {
            assertEquals("This role not found for selected user",
                    e.getMessage());
        }
    }

    @Test
    public void deleteRoleThisUserHasNotThisRole() {

        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        when(userRoleDao.isExistObject(any(UserRoleTbl.class))).thenReturn(false);

        try {
            managementRoleForUser.deleteRole(1L, 1L);
        } catch (ServiceException e) {
            assertEquals("This user has not this role",
                    e.getMessage());
        }
    }

}