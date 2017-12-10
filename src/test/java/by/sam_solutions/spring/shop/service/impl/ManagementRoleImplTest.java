package by.sam_solutions.spring.shop.service.impl;

import by.sam_solutions.spring.shop.exception.ServiceException;
import by.sam_solutions.spring.shop.jdbc.dao.RoleDao;
import by.sam_solutions.spring.shop.jdbc.model.RoleTbl;
import by.sam_solutions.spring.shop.service.model.Role;
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
public class ManagementRoleImplTest {

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private ManagementRoleImpl managementRole;

    @Test
    public void addRole() {
        when(roleDao.findByName(anyString())).thenReturn(null);
        doNothing().when(roleDao).insert(any(RoleTbl.class));

        try {
            assertEquals(1,
                managementRole.addRole("TestRole"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void addRoleThisRoleAlreadyExists() {
        when(roleDao.findByName(anyString())).thenReturn(new RoleTbl());

        try {
            managementRole.addRole("TestRole");
        } catch (ServiceException e) {
            assertEquals("This role already exists", e.getMessage());
        }
    }

    @Test
    public void getAllRoles(){
        when(roleDao.getAllRoles()).thenReturn(new HashSet<RoleTbl>());
        assertEquals(0, managementRole.getAllRoles().size());
    }

    @Test
    public void findRoleById() {

        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl(){
            @Override
            public Long getRoleId() {
                return 123L;
            }

            @Override
            public String getRole() {
                return "TestRole";
            }
        });

        Role role = new Role();

        try {
            role = managementRole.findRoleById(123L);
        } catch (ServiceException e) {
            fail();
        }

        assertEquals((Long)123L, role.getRoleId());
        assertEquals("TestRole", role.getRole());
    }

    @Test
    public void findRoleByIdThisRoleIsNotFound() {

        when(roleDao.findById(anyLong())).thenReturn(null);

        try {
            managementRole.findRoleById(123L);
        } catch (ServiceException e) {
            assertEquals("This role is not found",
                    e.getMessage());
        }
    }

    @Test
    public void findRoleByName() {

        when(roleDao.findByName(anyString())).thenReturn(new RoleTbl(){
            @Override
            public Long getRoleId() {
                return 123L;
            }

            @Override
            public String getRole() {
                return "TestRole";
            }
        });

        Role role = new Role();

        try {
            role = managementRole.findRoleByName("TestRole");
        } catch (ServiceException e) {
            fail();
        }

        assertEquals((Long)123L, role.getRoleId());
        assertEquals("TestRole", role.getRole());
    }

    @Test
    public void findRoleByNameThisRoleIsNotFound() {

        when(roleDao.findByName(anyString())).thenReturn(null);

        try {
            managementRole.findRoleByName("TestRole");
        } catch (ServiceException e) {
            assertEquals("This role is not found",
                    e.getMessage());
        }
    }

    @Test
    public void updateRole() {

        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        when(roleDao.findByName(anyString())).thenReturn(null);
        doNothing().when(roleDao).update(any(RoleTbl.class));

        try {
            assertEquals(1,
                managementRole.updateRole(123L, "TestRole"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void updateRoleThisRoleIsNotFound() {

        when(roleDao.findById(anyLong())).thenReturn(null);
        when(roleDao.findByName(anyString())).thenReturn(null);

        try {
            managementRole.updateRole(123L, "TestRole");
        } catch (ServiceException e) {
            assertEquals("This role is not found",
                    e.getMessage());
        }
    }

    @Test
    public void updateRoleThisRoleAlreadyExists() {

        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        when(roleDao.findByName(anyString())).thenReturn(new RoleTbl());

        try {
            managementRole.updateRole(123L, "TestRole");
        } catch (ServiceException e) {
            assertEquals("This role already exists",
                    e.getMessage());
        }
    }

    @Test
    public void deleteRole() {

        when(roleDao.findById(anyLong())).thenReturn(new RoleTbl());
        doNothing().when(roleDao).insert(any(RoleTbl.class));

        try {
            assertEquals(1,
                    managementRole.deleteRole(1L));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void deleteRoleThisRoleIsNotFound() {

        when(roleDao.findById(anyLong())).thenReturn(null);

        try {
            managementRole.deleteRole(1L);
        } catch (ServiceException e) {
            assertEquals("This role is not found",
                    e.getMessage());
        }
    }

}