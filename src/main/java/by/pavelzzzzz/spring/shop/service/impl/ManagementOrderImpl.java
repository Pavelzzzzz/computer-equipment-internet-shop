package by.pavelzzzzz.spring.shop.service.impl;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.jdbc.dao.OrderDao;
import by.pavelzzzzz.spring.shop.jdbc.model.OrderTbl;
import by.pavelzzzzz.spring.shop.jdbc.model.UserTbl;
import by.pavelzzzzz.spring.shop.service.ManagementOrder;
import by.pavelzzzzz.spring.shop.service.model.Order;
import by.pavelzzzzz.spring.shop.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ManagementOrderImpl implements ManagementOrder {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Set<Order> getAllOrders(){
        Set<Order> resultSet = new LinkedHashSet<Order>();
        Iterator<OrderTbl> iterator =
                orderDao.getAllOrders().iterator();
        while (iterator.hasNext()){
            OrderTbl foundOrder = iterator.next();
            resultSet.add(new Order(
                    foundOrder.getOrderId(),
                    foundOrder.getProductId(),
                    foundOrder.getUserId(),
                    foundOrder.getPhone(),
                    foundOrder.getAddress(),
                    foundOrder.getCount()));
        }
        return resultSet;
    }

    @Override
    public Set<Order> getAllOrdersByUser(User user){
        Set<Order> resultSet = new LinkedHashSet<Order>();
        Iterator<OrderTbl> iterator =
                orderDao.getAllOrdersByUser( new UserTbl(
                        user.getUserId(),
                        user.getLogin(),
                        user.getEmail(),
                        user.isActive())
                ).iterator();
        while (iterator.hasNext()){
            OrderTbl foundOrder = iterator.next();
            resultSet.add(new Order(
                    foundOrder.getOrderId(),
                    foundOrder.getProductId(),
                    foundOrder.getUserId(),
                    foundOrder.getPhone(),
                    foundOrder.getAddress(),
                    foundOrder.getCount()));
        }
        return resultSet;
    }

    @Override
    public int addOrder (Long productId, Long userId, String phone, String address, Long count) throws ServiceException {

        orderDao.insert(new OrderTbl(
                null, productId, userId, phone, address, count));
        return 1;
    }

    @Override
    public Order findOrderById(Long orderId) throws ServiceException {
        OrderTbl foundOrder = orderDao.findById(orderId);

        if  ( foundOrder == null) {
            throw new ServiceException("This order is not found");
        }

        Order product = new Order(
                foundOrder.getOrderId(),
                foundOrder.getProductId(),
                foundOrder.getUserId(),
                foundOrder.getPhone(),
                foundOrder.getAddress(),
                foundOrder.getCount()
        );

        return product;
    }

    @Override
    public int updateOrder(Order order) throws ServiceException {
        OrderTbl foundOrder = orderDao.findById(order.getOrderId());

        if  ( foundOrder == null) {
            throw new ServiceException("This order is not found");
        }

        foundOrder.setProductId(order.getProductId());
        foundOrder.setUserId(order.getUserId());
        foundOrder.setPhone(order.getPhone());
        foundOrder.setAddress(order.getAddress());
        foundOrder.setCount(order.getCount());

            orderDao.update(foundOrder);

        return 1;
    }

    @Override
    public int deleteOrderById (Long orderId) throws ServiceException {
        OrderTbl foundOrder = orderDao.findById(orderId);

        if  ( foundOrder == null) {
            throw new ServiceException("This order is not found");
        }

        orderDao.delete(foundOrder);

        return 1;
    }
}
