package by.pavelzzzzz.spring.shop.jdbc.dao.impl;

import by.pavelzzzzz.spring.shop.jdbc.dao.UserDao;
import by.pavelzzzzz.spring.shop.jdbc.model.UserTbl;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ContextConfiguration(locations = {"classpath:Spring-Module.xml",
        "classpath:database/Flyway_Spring-DataSourceH2.xml"})
public class UserDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @After
    public void tearDown(){
        ResourceDatabasePopulator tables =
                new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("sql_script/clean_tables.sql"));
        tables.addScript(new ClassPathResource("sql_script/insert_data.sql"));
        DatabasePopulatorUtils.execute(tables, userDao.getDataSource());
    }

    @Test
    public void getAllUsers(){
        assertEquals(1,
                userDao.getAllUsers().size());
    }

    @Test
    public void findById(){
        UserTbl foundUser = userDao.findById(1L);

        assertNotNull(foundUser);
    }

    @Test
    public void findByIdNegative(){
        UserTbl foundUser = userDao.findById(255L);

        assertNull(foundUser);
    }

    @Test
    public void findByLogin(){
        UserTbl foundUser = userDao.findByLogin("user");

        assertNotNull(foundUser);
    }

    @Test
    public void findByLoginNegative(){
        UserTbl foundUser = userDao.findByLogin("user564654123154");

        assertNull( foundUser);
    }

    @Test
    public void insert(){
        UserTbl user = new UserTbl();

        user.setLogin("TestLogin");
        user.setEmail("TestEmail@test.com");
        user.setActive(true);

        userDao.insert(user);

        UserTbl user1 = userDao.findById(2L);

        assertEquals(user.getLogin(), user1.getLogin());
        assertEquals(user.getEmail(), user1.getEmail());
        assertEquals(user.isActive(), user1.isActive());
    }

    @Test
    public void insertNegativeOnlySetEmail(){
        UserTbl user = new UserTbl();

        user.setEmail("sdg");

        userDao.insert(user);

        UserTbl foundUser = userDao.findById(2L);

        assertNull(foundUser);
    }

    @Test
    public void update(){
        UserTbl user = userDao.findById(1L);

        user.setLogin("changed " + user.getLogin());
        user.setEmail("changed " + user.getEmail());
        user.setActive(!user.isActive());

        userDao.update(user);

        UserTbl foundUser = userDao.findById(user.getUserId());

        assertEquals(user.getLogin(), foundUser.getLogin());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.isActive(), foundUser.isActive());
    }

    @Test
    public void updateNegative(){
        UserTbl user = new UserTbl();

        user.setUserId(3456L);
        user.setLogin("testLogin ");
        user.setEmail("testEmail ");

        userDao.update(user);

        UserTbl foundUser = userDao.findById(user.getUserId());

        assertNull(foundUser);
    }

    @Test
    public void delete(){
        UserTbl user = userDao.findById(1L);
        userDao.delete(user);
        assertNull(userDao.findById(1L));
    }

    @Test
    public void deleteNegative(){
        UserTbl user = new UserTbl();

        user.setUserId(4563654L);

        assertNull(userDao.findById(user.getUserId()));

        userDao.delete(user);

        assertNull(userDao.findById(user.getUserId()));
    }

}