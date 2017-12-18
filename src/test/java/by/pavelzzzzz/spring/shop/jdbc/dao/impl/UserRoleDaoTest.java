package by.pavelzzzzz.spring.shop.jdbc.dao.impl;

import by.pavelzzzzz.spring.shop.jdbc.dao.UserRoleDao;
import by.pavelzzzzz.spring.shop.jdbc.model.UserRoleTbl;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:Spring-Module.xml",
        "classpath:database/Flyway_Spring-DataSourceH2.xml"})
public class UserRoleDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @Qualifier("userRoleDao")
    private UserRoleDao userRoleDao;

    @After
    public void tearDown(){
        ResourceDatabasePopulator tables =
                new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("sql_script/clean_tables.sql"));
        tables.addScript(new ClassPathResource("sql_script/insert_data.sql"));
        DatabasePopulatorUtils.execute(tables, userRoleDao.getDataSource());
    }

    @Test
    public void getUserRoleDao(){
        assertEquals(1, userRoleDao.getAllRoles(1L).size());
    }

    @Test
    public void isExistObject(){
        UserRoleTbl userRole = new UserRoleTbl();

        userRole.setUserId(1L);
        userRole.setRoleId(1L);

        assertTrue(userRoleDao.isExistObject(userRole));
    }

    @Test
    public void insert(){

        delete();

        UserRoleTbl userRole = new UserRoleTbl();

        userRole.setUserId(1L);
        userRole.setRoleId(1L);

        userRoleDao.insert(userRole);

        assertTrue(userRoleDao.isExistObject(userRole));
    }

    @Test
    public void delete(){
        UserRoleTbl userRole = new UserRoleTbl();

        userRole.setUserId(1L);
        userRole.setRoleId(1L);

        assertTrue(userRoleDao.isExistObject(userRole));

        userRoleDao.delete(userRole);

        assertFalse(userRoleDao.isExistObject(userRole));
    }

}