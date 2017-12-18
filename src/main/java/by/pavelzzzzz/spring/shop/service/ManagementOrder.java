package by.pavelzzzzz.spring.shop.service;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.service.model.Order;
import by.pavelzzzzz.spring.shop.service.model.User;

import java.util.Set;

public interface ManagementOrder {

    Set<Order> getAllOrders();

    Set<Order> getAllOrdersByUser(User user);

    int addOrder(Long productId, Long userId, String phone, String address, Long count) throws ServiceException;

    Order findOrderById(Long orderId) throws ServiceException;

    int updateOrder(Order order) throws ServiceException;

    int deleteOrderById(Long orderId) throws ServiceException;
}
