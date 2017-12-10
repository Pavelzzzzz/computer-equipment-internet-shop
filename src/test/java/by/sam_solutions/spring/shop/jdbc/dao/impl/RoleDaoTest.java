package by.sam_solutions.spring.shop.jdbc.dao.impl;

import by.sam_solutions.spring.shop.jdbc.dao.RoleDao;
import by.sam_solutions.spring.shop.jdbc.model.RoleTbl;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ContextConfiguration(locations = {"classpath:Spring-Module.xml",
        "classpath:database/Flyway_Spring-DataSourceH2.xml"})
public class RoleDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;

    @After
    public void tearDown(){
        ResourceDatabasePopulator tables =
                new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("sql_script/clean_tables.sql"));
        tables.addScript(new ClassPathResource("sql_script/insert_data.sql"));
        DatabasePopulatorUtils.execute(tables, roleDao.getDataSource());
    }

    @Test
    public void getAllRoles(){
        assertEquals(1, roleDao.getAllRoles().size());
    }

    @Test
    public void findById(){
        RoleTbl foundRole = roleDao.findById(1L);

        assertNotNull(foundRole);
    }

    @Test
    public void findByName(){
        RoleTbl foundRole = roleDao.findByName("Admin");
        assertNotEquals(null, foundRole);
    }

    @Test
    public void insert(){
        RoleTbl role = new RoleTbl();

        role.setRole("User");

        roleDao.insert(role);

        RoleTbl role1 = roleDao.findById(2L);

        assertEquals(role.getRole(), role1.getRole());
    }

    @Test
    public void update(){
        RoleTbl role = roleDao.findById(1L);

        role.setRole("Administrator");

        roleDao.update(role);

        RoleTbl foundRole = roleDao.findById(role.getRoleId());

        assertEquals(role.getRole(), foundRole.getRole());
    }

    @Test
    public void delete(){
        RoleTbl role = roleDao.findById(1L);
        roleDao.delete(role);
        assertNull(roleDao.findById(1L));
    }

}