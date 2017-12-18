package by.pavelzzzzz.spring.shop.jdbc.dao.impl;

import by.pavelzzzzz.spring.shop.jdbc.dao.PasswordDao;
import by.pavelzzzzz.spring.shop.jdbc.model.PasswordTbl;
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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@ContextConfiguration(locations = {"classpath:Spring-Module.xml",
        "classpath:database/Flyway_Spring-DataSourceH2.xml"})
public class PasswordDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @Qualifier("passwordDao")
    private PasswordDao passwordDao;

    @After
    public void tearDown(){
        ResourceDatabasePopulator tables =
                new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("sql_script/clean_tables.sql"));
        tables.addScript(new ClassPathResource("sql_script/insert_data.sql"));
        DatabasePopulatorUtils.execute(tables, passwordDao.getDataSource());
    }

    @Test
    public void findByUserId(){
        PasswordTbl foundPassword = passwordDao.findByUserId(1L);

        assertNotEquals(null, foundPassword);
    }

    @Test
    public void insert(){

        delete();

        PasswordTbl password = new PasswordTbl();

        password.setUserId(1L);
        password.setPassword("789456");

        passwordDao.insert(password);

        PasswordTbl foundPassword = passwordDao.findByUserId(1L);

        assertEquals(password.getPassword(), foundPassword.getPassword());
    }

    @Test
    public void update(){
        PasswordTbl password = passwordDao.findByUserId(1L);

        password.setPassword("987654321");

        passwordDao.update(password);

        PasswordTbl foundPassword = passwordDao.findByUserId(password.getUserId());

        assertEquals(password.getPassword(), foundPassword.getPassword());
    }

    @Test
    public void delete(){
        PasswordTbl password = passwordDao.findByUserId(1L);
        passwordDao.delete(password);
        assertNull(passwordDao.findByUserId(1L));
    }

}