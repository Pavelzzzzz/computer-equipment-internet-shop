package by.sam_solutions.spring.shop.jdbc.dao.impl;

import by.sam_solutions.spring.shop.jdbc.dao.CategoryDao;
import by.sam_solutions.spring.shop.jdbc.model.CategoryTbl;
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
public class CategoryDaoImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @Qualifier("categoryDao")
    private CategoryDao categoryDao;

    @After
    public void tearDown(){
        ResourceDatabasePopulator tables =
                new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("sql_script/clean_tables.sql"));
        tables.addScript(new ClassPathResource("sql_script/insert_data.sql"));
        DatabasePopulatorUtils.execute(tables, categoryDao.getDataSource());
    }

    @Test
    public void getAllCategory() {
        assertEquals(1, categoryDao.getAllCategory().size());
    }

    @Test
    public void findById() {
        assertNotNull(categoryDao.findById(1L));
    }

    @Test
    public void findByName() {
        assertNotNull(categoryDao.findByName("Phone"));
    }

    @Test
    public void insert() {
        String testCategory = "Test category";
        categoryDao.insert(new CategoryTbl(2L, testCategory));
        assertNotNull(categoryDao.findByName(testCategory));
    }

    @Test
    public void update() {
        CategoryTbl foundCategory = categoryDao.findByName("Phone");
        String testCategory = "Test category";
        foundCategory.setCategory(testCategory);
        categoryDao.update(foundCategory);
        assertNotNull(categoryDao.findByName(testCategory));
    }

    @Test
    public void delete() {
        categoryDao.delete(categoryDao.findByName("Phone"));
        assertNull(categoryDao.findByName("Phone"));
    }

}