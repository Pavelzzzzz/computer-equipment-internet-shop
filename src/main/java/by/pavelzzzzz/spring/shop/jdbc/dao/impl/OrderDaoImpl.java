package by.pavelzzzzz.spring.shop.jdbc.dao.impl;

import by.pavelzzzzz.spring.shop.jdbc.dao.OrderDao;
import by.pavelzzzzz.spring.shop.jdbc.dao.ProductDao;
import by.pavelzzzzz.spring.shop.jdbc.model.CategoryTbl;
import by.pavelzzzzz.spring.shop.jdbc.model.OrderTbl;
import by.pavelzzzzz.spring.shop.jdbc.model.ProductTbl;
import by.pavelzzzzz.spring.shop.jdbc.model.UserTbl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);

    public static final String SQL_GET_ALL_ORDERS = "SELECT * FROM " +
            OrderTbl.TABLE_NAME + " ORDER BY " + OrderTbl.ORDER_ID_COLUMN;
    public static final String SQL_GET_ALL_ORDERS_BY_USER_ID = "SELECT * FROM " +
            OrderTbl.TABLE_NAME + " WHERE " + OrderTbl.USER_ID_COLUMN + " = ? " +
            " ORDER BY " + OrderTbl.ORDER_ID_COLUMN;
    public static final String SQL_FIND_BY_ID = "SELECT * FROM " +
            OrderTbl.TABLE_NAME + " WHERE " + OrderTbl.ORDER_ID_COLUMN + " = ?";
    public static final String SQL_INSERT = "INSERT INTO " +
            OrderTbl.TABLE_NAME + " (" +
            OrderTbl.PRODUCT_ID_COLUMN + ", " +
            OrderTbl.USER_ID_COLUMN + ", " +
            OrderTbl.PHONE_COLUMN + ", " +
            OrderTbl.ADDRESS_COLUMN + ", " +
            OrderTbl.COUNT_COLUMN +  ") VALUES(?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE = "UPDATE " + OrderTbl.TABLE_NAME +
            " SET " + OrderTbl.PRODUCT_ID_COLUMN + " = ?, " +
            OrderTbl.USER_ID_COLUMN + " = ?, " +
            OrderTbl.PHONE_COLUMN + " = ?, " +
            OrderTbl.ADDRESS_COLUMN + " = ?, " +
            OrderTbl.COUNT_COLUMN + " = ? WHERE " +
            OrderTbl.ORDER_ID_COLUMN + " = ?";
    public static final String SQL_DELETE = "DELETE FROM " + OrderTbl.TABLE_NAME +
            " WHERE " + OrderTbl.ORDER_ID_COLUMN + " = ?";

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public Set<OrderTbl> getAllOrders(){
        Set<OrderTbl> resultSet = new LinkedHashSet<>();
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_ORDERS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                resultSet.add(new OrderTbl(
                        rs.getLong(OrderTbl.ORDER_ID_COLUMN),
                        rs.getLong(OrderTbl.PRODUCT_ID_COLUMN),
                        rs.getLong(OrderTbl.USER_ID_COLUMN),
                        rs.getString(OrderTbl.PHONE_COLUMN),
                        rs.getString(OrderTbl.ADDRESS_COLUMN),
                        rs.getLong(OrderTbl.COUNT_COLUMN)));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException ", e.getMessage());
        }
        return resultSet;
    }

    @Override
    public Set<OrderTbl> getAllOrdersByUser(UserTbl userTbl){
        Set<OrderTbl> resultSet = new LinkedHashSet<>();
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_ORDERS_BY_USER_ID);
            statement.setLong(1, userTbl.getUserId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                resultSet.add(new OrderTbl(
                        rs.getLong(OrderTbl.ORDER_ID_COLUMN),
                        rs.getLong(OrderTbl.PRODUCT_ID_COLUMN),
                        rs.getLong(OrderTbl.USER_ID_COLUMN),
                        rs.getString(OrderTbl.PHONE_COLUMN),
                        rs.getString(OrderTbl.ADDRESS_COLUMN),
                        rs.getLong(OrderTbl.COUNT_COLUMN)));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException ", e.getMessage());
        }
        return resultSet;
    }

    @Override
    public OrderTbl findById(Long orderId) {
        OrderTbl orderTbl = null;
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, orderId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                orderTbl = new OrderTbl(
                        rs.getLong(OrderTbl.ORDER_ID_COLUMN),
                        rs.getLong(OrderTbl.PRODUCT_ID_COLUMN),
                        rs.getLong(OrderTbl.USER_ID_COLUMN),
                        rs.getString(OrderTbl.PHONE_COLUMN),
                        rs.getString(OrderTbl.ADDRESS_COLUMN),
                        rs.getLong(OrderTbl.COUNT_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return orderTbl;
    }

    @Override
    public void insert(OrderTbl orderTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, orderTbl.getProductId());
            statement.setLong(2, orderTbl.getUserId());
            statement.setString(3, orderTbl.getPhone());
            statement.setString(4, orderTbl.getAddress());
            statement.setLong(5, orderTbl.getCount());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void update(OrderTbl orderTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setLong(1, orderTbl.getProductId());
            statement.setLong(2, orderTbl.getUserId());
            statement.setString(3, orderTbl.getPhone());
            statement.setString(4, orderTbl.getAddress());
            statement.setLong(5, orderTbl.getCount());
            statement.setLong(6, orderTbl.getOrderId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void delete(OrderTbl orderTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, orderTbl.getOrderId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }
}
