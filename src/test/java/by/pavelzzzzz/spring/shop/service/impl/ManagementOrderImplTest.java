package by.pavelzzzzz.spring.shop.service.impl;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.jdbc.dao.OrderDao;
import by.pavelzzzzz.spring.shop.jdbc.model.OrderTbl;
import by.pavelzzzzz.spring.shop.jdbc.model.UserTbl;
import by.pavelzzzzz.spring.shop.service.model.Order;
import by.pavelzzzzz.spring.shop.service.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ManagementOrderImplTest {
    
    @Mock
    private OrderDao orderDao;
    
    @InjectMocks
    private ManagementOrderImpl managementOrder;
    
    @Test
    public void getAllOrders() {
        when(orderDao.getAllOrders()).thenReturn(new LinkedHashSet<>());

        assertEquals(0,
                managementOrder.getAllOrders().size());
    }

    @Test
    public void getAllOrdersByUser() {
        when(orderDao.getAllOrdersByUser(any(UserTbl.class)))
                .thenReturn(new LinkedHashSet<>());

        assertEquals(0,
                managementOrder.getAllOrdersByUser(new User()).size());
    }

    @Test
    public void addOrder() {
        doNothing().when(orderDao).insert(any(OrderTbl.class));

        try {
            assertEquals(1,
                    managementOrder.addOrder(
                            1L,
                            1L,
                            "test phone",
                            "test address",
                            15L));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void findOrderById() {
        when(orderDao.findById(anyLong())).thenReturn(new OrderTbl());

        try {
            assertEquals(Order.class,
                    managementOrder.findOrderById(123L).getClass());
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void findProductByIdThisProductIsNotFound() {
        when(orderDao.findById(anyLong())).thenReturn(null);

        try {
            managementOrder.findOrderById(123L);
        } catch (ServiceException e) {
            assertEquals("This order is not found",
                    e.getMessage());
        }
    }

    @Test
    public void updateOrder() {
        when(orderDao.findById(anyLong())).thenReturn(new OrderTbl());
        when(orderDao.findById(anyLong())).thenReturn(new OrderTbl());
        doNothing().when(orderDao).update(any(OrderTbl.class));

        try {
            assertEquals(1,
                    managementOrder.updateOrder(new Order()));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void updateOrderThisOrderIsNotFound() {
        when(orderDao.findById(anyLong())).thenReturn(null);

        try {
            managementOrder.updateOrder(new Order());
        } catch (ServiceException e) {
            assertEquals("This order is not found",
                    e.getMessage());
        }
    }

    @Test
    public void deleteOrderById() {
        when(orderDao.findById(anyLong())).thenReturn(new OrderTbl());
        doNothing().when(orderDao).delete(any(OrderTbl.class));

        try {
            assertEquals(1,
                    managementOrder.deleteOrderById(123L));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void deleteOrderByIdThisOrderIsNotFound() {
        when(orderDao.findById(anyLong())).thenReturn(null);

        try {
            managementOrder.deleteOrderById(123L);
        } catch (ServiceException e) {
            assertEquals("This order is not found",
                    e.getMessage());
        }
    }

}