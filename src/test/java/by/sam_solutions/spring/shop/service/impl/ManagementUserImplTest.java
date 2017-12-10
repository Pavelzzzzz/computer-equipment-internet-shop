package by.sam_solutions.spring.shop.service.impl;

import by.sam_solutions.spring.shop.exception.ServiceException;
import by.sam_solutions.spring.shop.jdbc.dao.UserDao;
import by.sam_solutions.spring.shop.jdbc.model.UserTbl;
import by.sam_solutions.spring.shop.service.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ManagementUserImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private ManagementUserImpl managementUser;

    @Test
    public void getAllUser(){
        when(userDao.getAllUsers()).thenReturn(new HashSet<UserTbl>());

        assertEquals(0,
                managementUser.getAllUsers().size());
    }

    @Test
    public void addUser() {
        when(userDao.findByLogin(anyString())).thenReturn(null);
        doNothing().when(userDao).insert(any(UserTbl.class));

        try {
            assertEquals(1,
                    managementUser.addUser("testLogin", "testEmail"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void addUserThisLoginAlreadyExists() {
        when(userDao.findByLogin(anyString())).thenReturn(new UserTbl());

        try {
            managementUser.addUser("testLogin", "testEmail");
        } catch (ServiceException e) {
            assertEquals("This login already exists: testLogin",
                    e.getMessage());
        }
    }

    @Test
    public void findUserById() {
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());

        try {
            assertEquals(User.class,
                    managementUser.findUserById(123L).getClass());
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void findUserByIdThisUserIsNotFound() {
        when(userDao.findById(anyLong())).thenReturn(null);

        try {
            managementUser.findUserById(123L);
        } catch (ServiceException e) {
            assertEquals("This user is not found",
                    e.getMessage());
        }
    }

    @Test
    public void findUserByLogin() {
        when(userDao.findByLogin(anyString())).thenReturn(new UserTbl());

        try {
            assertEquals(User.class,
                    managementUser.findUserByLogin("user1").getClass());
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void findUserByLoginThisUserIsNotFound() {
        when(userDao.findByLogin(anyString())).thenReturn(null);

        try {
            managementUser.findUserByLogin("flalseUser");
        } catch (ServiceException e) {
            assertEquals("This user is not found",
                    e.getMessage());
        }
    }

    @Test
    public void updateUser() {
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(userDao.findByLogin(anyString())).thenReturn(null, new UserTbl(){
        @Override
        public Long getUserId(){
            return 123L;
        }
        });
        doNothing().when(userDao).update(any(UserTbl.class));

        try {
            assertEquals(1,
                    managementUser.updateUser(new User()));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void updateUserThisUserIsNotFound() {
        when(userDao.findById(anyLong())).thenReturn(null);
        when(userDao.findByLogin(anyString())).thenReturn(null);
        doNothing().when(userDao).update(any(UserTbl.class));

        try {
            managementUser.updateUser(new User());
        } catch (ServiceException e) {
            assertEquals("This user is not found",
                    e.getMessage());
        }
    }

    @Test
    public void updateUserThisLoginAlreadyExists() {
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(userDao.findByLogin(anyString())).thenReturn(new UserTbl());
        doNothing().when(userDao).update(any(UserTbl.class));

        try {
            managementUser.updateUser(new User());
        } catch (ServiceException e) {
            assertEquals("This login already exists",
                    e.getMessage());
        }
    }

    @Test
    public void deleteUserById() {
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        doNothing().when(userDao).delete(any(UserTbl.class));


        try {
            assertEquals(1,
                    managementUser.deleteUserById(123L));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void deleteUserByIdThisUserIsNotFound() {
        when(userDao.findById(anyLong())).thenReturn(null);

        try {
            managementUser.deleteUserById(123L);
        } catch (ServiceException e) {
            assertEquals("This user is not found",
                    e.getMessage());
        }
    }

}