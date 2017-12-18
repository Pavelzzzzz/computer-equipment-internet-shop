package by.pavelzzzzz.spring.shop.jdbc.dao.impl;

import by.pavelzzzzz.spring.shop.jdbc.dao.OrderDao;
import by.pavelzzzzz.spring.shop.jdbc.model.OrderTbl;
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
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:Spring-Module.xml",
        "classpath:database/Flyway_Spring-DataSourceH2.xml"})
public class OrderDaoImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;

    @After
    public void tearDown(){
        ResourceDatabasePopulator tables =
                new ResourceDatabasePopulator();
        tables.addScript(new ClassPathResource("sql_script/clean_tables.sql"));
        tables.addScript(new ClassPathResource("sql_script/insert_data.sql"));
        DatabasePopulatorUtils.execute(tables, orderDao.getDataSource());
    }

    @Test
    public void getAllOrders() {
        assertEquals(1,
                orderDao.getAllOrders().size());
    }

    @Test
    public void getAllOrdersByCategory() {
        assertEquals(1,
                orderDao.getAllOrdersByUser(
                        new UserTbl(1L, null, null, false)).size());
    }

    @Test
    public void findById() {
        assertNotNull(orderDao.findById(1L));
    }

    @Test
    public void insert() {
        orderDao.insert(new OrderTbl(null,
                1L,
                1L,
                "test phone",
                "test address",
                16L));
        assertEquals(2, orderDao.getAllOrders().size());
    }

    @Test
    public void update() {
        OrderTbl foundOrder = orderDao.findById(1L);
        foundOrder.setAddress("Сhanged address");
        orderDao.update(foundOrder);
        assertTrue(orderDao.findById(1L).getAddress().equals("Сhanged address"));
    }

    @Test
    public void delete() {
        orderDao.delete(orderDao.findById(1L));
        assertNull(orderDao.findById(1L));
    }

}