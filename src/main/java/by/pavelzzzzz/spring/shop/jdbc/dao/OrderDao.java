package by.pavelzzzzz.spring.shop.jdbc.dao;

import by.pavelzzzzz.spring.shop.jdbc.model.OrderTbl;
import by.pavelzzzzz.spring.shop.jdbc.model.UserTbl;

import javax.sql.DataSource;
import java.util.Set;

public interface OrderDao {

    DataSource getDataSource();

    Set<OrderTbl> getAllOrders();

    Set<OrderTbl> getAllOrdersByUser(UserTbl userTbl);

    OrderTbl findById(Long orderId);

    void insert(OrderTbl orderTbl);

    void update(OrderTbl orderTbl);

    void delete(OrderTbl orderTbl);
}
