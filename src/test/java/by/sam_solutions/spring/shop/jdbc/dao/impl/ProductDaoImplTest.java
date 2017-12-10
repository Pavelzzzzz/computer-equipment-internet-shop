package by.sam_solutions.spring.shop.jdbc.dao.impl;

import by.sam_solutions.spring.shop.jdbc.dao.ProductDao;
import by.sam_solutions.spring.shop.jdbc.model.CategoryTbl;
import by.sam_solutions.spring.shop.jdbc.model.ProductTbl;
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
public class ProductDaoImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @Qualifier("productDao")
    private ProductDao productDao;

    @After
    public void tearDown(){
        ResourceDatabasePopulator tables =
                new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("sql_script/clean_tables.sql"));
        tables.addScript(new ClassPathResource("sql_script/insert_data.sql"));
        DatabasePopulatorUtils.execute(tables, productDao.getDataSource());
    }

    @Test
    public void getAllProducts() {
        assertEquals(1,
                productDao.getAllProducts().size());
    }

    @Test
    public void getAllProductsByCategory() {
        assertEquals(1,
                productDao.getAllProductsByCategory(
                        new CategoryTbl(1L, "Phone")).size());
    }

    @Test
    public void findById() {
        assertNotNull(productDao.findById(1L));
    }

    @Test
    public void findByTitle() {
        assertNotNull(productDao.findByTitle("Title"));
    }

    @Test
    public void insert() {
        productDao.insert(new ProductTbl(2L, 1L, "Title2", 1, 15, "Some text"));
        assertNotNull(productDao.findByTitle("Title2"));
    }

    @Test
    public void update() {
        ProductTbl foundProduct = productDao.findByTitle("Title");
        foundProduct.setTitle("Сhanged title");
        productDao.update(foundProduct);
        assertNotNull(productDao.findByTitle("Сhanged title"));
    }

    @Test
    public void delete() {
        productDao.delete(productDao.findByTitle("Title"));
        assertNull(productDao.findByTitle("Title"));
    }

}