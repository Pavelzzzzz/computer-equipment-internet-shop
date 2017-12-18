package by.pavelzzzzz.spring.shop.service.impl;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.jdbc.dao.PasswordDao;
import by.pavelzzzzz.spring.shop.jdbc.dao.UserDao;
import by.pavelzzzzz.spring.shop.jdbc.model.PasswordTbl;
import by.pavelzzzzz.spring.shop.jdbc.model.UserTbl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ManagementPasswordForUserImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private PasswordDao passwordDao;

    @InjectMocks
    private ManagementPasswordForUserImpl managementPasswordForUser;

    @Test
    public void setPassword() {
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(passwordDao.findByUserId(anyLong())).thenReturn(null);
        doNothing().when(passwordDao).insert(any(PasswordTbl.class));

        try {
            assertEquals(1,
                managementPasswordForUser.setPassword(123L, "TestPassword"));
        } catch (ServiceException e) {
            fail();
        }

    }

    @Test
    public void setPasswordUserNotFound() {
        when(userDao.findById(anyLong())).thenReturn(null);
        when(passwordDao.findByUserId(anyLong())).thenReturn(null);

        String errorMessage = "";

        try {
            managementPasswordForUser.setPassword(123L, "TestPassword");
        } catch (ServiceException e) {
            errorMessage = e.getMessage();
        }

        assertEquals(errorMessage, "User not found");
    }

    @Test
    public void setPasswordIsAlreadySet() {
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(passwordDao.findByUserId(anyLong())).thenReturn(new PasswordTbl());

        String errorMessage = "";

        try {
            managementPasswordForUser.setPassword(123L, "TestPassword");
        } catch (ServiceException e) {
            errorMessage = e.getMessage();
        }

        assertEquals(errorMessage, "Password is already set for this user");
    }

    @Test
    public void updatePassword() {
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(passwordDao.findByUserId(anyLong())).thenReturn(new PasswordTbl());
        doNothing().when(passwordDao).update(any(PasswordTbl.class));

        try {
            assertEquals(1,
                managementPasswordForUser.updatePassword(123L, "TestPassword"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void updatePasswordUserNotFound() {
        when(userDao.findById(anyLong())).thenReturn(null);
        when(passwordDao.findByUserId(anyLong())).thenReturn(null);

        String errorMessage = "";

        try {
            managementPasswordForUser.updatePassword(123L, "TestPassword");
        } catch (ServiceException e) {
            errorMessage = e.getMessage();
        }

        assertEquals(errorMessage, "User not found");
    }

    @Test
    public void updatePasswordIsNotAlreadySet() {
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(passwordDao.findByUserId(anyLong())).thenReturn(null);

        String errorMessage = "";

        try {
            managementPasswordForUser.updatePassword(123L, "TestPassword");
        } catch (ServiceException e) {
            errorMessage = e.getMessage();
        }

        assertEquals(errorMessage, "Password is not already set for this user");
    }

    @Test
    public void passwordVerification() {
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(passwordDao.findByUserId(anyLong())).thenReturn(new PasswordTbl(){
            @Override
            public String getPassword() {
                return "TestPassword";
            }
        });

        try {
            assertEquals(1,
                managementPasswordForUser.passwordVerification(123L, "TestPassword"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void passwordVerificationFalse() {
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(passwordDao.findByUserId(anyLong())).thenReturn(new PasswordTbl(){
            @Override
            public String getPassword() {
                return "TestPassword";
            }
        });

        try {
            assertEquals(0,
                    managementPasswordForUser.passwordVerification(123L, "FalseTestPassword"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void passwordVerificationUserNotFound(){
        when(userDao.findById(anyLong())).thenReturn(null);
        when(passwordDao.findByUserId(anyLong())).thenReturn(new PasswordTbl(){
            @Override
            public String getPassword() {
                return "TestPassword";
            }
        });

        try {
            managementPasswordForUser.passwordVerification(123L, "TestPassword");
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), "User not found");
        }
    }

    @Test
    public void passwordVerificationPasswordIsNotAlreadySet(){
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(passwordDao.findByUserId(anyLong())).thenReturn(null);

        try {
            managementPasswordForUser.passwordVerification(123L, "TestPassword");
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), "Password is not already set for this user");
        }
    }

    @Test
    public void deletePassword(){
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(passwordDao.findByUserId(anyLong())).thenReturn(new PasswordTbl());
        doNothing().when(passwordDao).delete(any(PasswordTbl.class));

        try {
            assertEquals(1,
                    managementPasswordForUser.deletePassword(123L));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void deletePasswordUserNotFound(){
        when(userDao.findById(anyLong())).thenReturn(null);
        when(passwordDao.findByUserId(anyLong())).thenReturn(new PasswordTbl());

        try {
                    managementPasswordForUser.deletePassword(123L);
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), "User not found");
        }
    }

    @Test
    public void deletePasswordIsNotAlreadySet(){
        when(userDao.findById(anyLong())).thenReturn(new UserTbl());
        when(passwordDao.findByUserId(anyLong())).thenReturn(null);

        try {
            managementPasswordForUser.deletePassword(123L);
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), "Password is not already set for this user");
        }
    }
}