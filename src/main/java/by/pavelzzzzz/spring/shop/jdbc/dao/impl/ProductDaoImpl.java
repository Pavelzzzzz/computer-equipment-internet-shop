package by.pavelzzzzz.spring.shop.jdbc.dao.impl;

import by.pavelzzzzz.spring.shop.jdbc.dao.ProductDao;
import by.pavelzzzzz.spring.shop.jdbc.model.CategoryTbl;
import by.pavelzzzzz.spring.shop.jdbc.model.ProductTbl;
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
public class ProductDaoImpl implements ProductDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);

    public static final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM " +
            ProductTbl.TABLE_NAME + " ORDER BY " + ProductTbl.TITLE_COLUMN;
    public static final String SQL_GET_ALL_PRODUCTS_BY_CATEGORY = "SELECT * FROM " +
            ProductTbl.TABLE_NAME + " WHERE " + ProductTbl.CATEGORY_ID_COLUMN + " = ? " +
            " ORDER BY " + ProductTbl.TITLE_COLUMN;
    public static final String SQL_FIND_BY_ID = "SELECT * FROM " +
            ProductTbl.TABLE_NAME + " WHERE " + ProductTbl.PRODUCT_ID_COLUMN + " = ?";
    public static final String SQL_FIND_BY_TITLE = "SELECT * FROM " +
            ProductTbl.TABLE_NAME + " WHERE " + ProductTbl.TITLE_COLUMN + " = ?";
    public static final String SQL_INSERT = "INSERT INTO " +
            ProductTbl.TABLE_NAME + " (" + ProductTbl.CATEGORY_ID_COLUMN + ", " +
                                        ProductTbl.TITLE_COLUMN + ", " +
                                        ProductTbl.COST_INTEGER_COLUMN + ", " +
                                        ProductTbl.COST_FRACTIONAL_COLUMN + ", " +
                                        ProductTbl.TEXT_COLUMN +  ") VALUES(?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE = "UPDATE " + ProductTbl.TABLE_NAME +
            " SET " + ProductTbl.CATEGORY_ID_COLUMN + " = ?, " +
            ProductTbl.TITLE_COLUMN + " = ?, " +
            ProductTbl.COST_INTEGER_COLUMN + " = ?, " +
            ProductTbl.COST_FRACTIONAL_COLUMN + " = ?, " +
            ProductTbl.TEXT_COLUMN + " = ? WHERE " +
            ProductTbl.PRODUCT_ID_COLUMN + " = ?";
    public static final String SQL_DELETE = "DELETE FROM " + ProductTbl.TABLE_NAME +
            " WHERE " + ProductTbl.PRODUCT_ID_COLUMN + " = ?";

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
    public Set<ProductTbl> getAllProducts(){
        Set<ProductTbl> resultSet = new LinkedHashSet<>();
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_PRODUCTS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                resultSet.add(new ProductTbl(
                        rs.getLong(ProductTbl.PRODUCT_ID_COLUMN),
                        rs.getLong(ProductTbl.CATEGORY_ID_COLUMN),
                        rs.getString(ProductTbl.TITLE_COLUMN),
                        rs.getInt(ProductTbl.COST_INTEGER_COLUMN),
                        rs.getInt(ProductTbl.COST_FRACTIONAL_COLUMN),
                        rs.getString(ProductTbl.TEXT_COLUMN)));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException ", e.getMessage());
        }
        return resultSet;
    }

    @Override
    public Set<ProductTbl> getAllProductsByCategory(CategoryTbl categoryTbl){
        Set<ProductTbl> resultSet = new LinkedHashSet<>();
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_CATEGORY);
            statement.setLong(1, categoryTbl.getCategoryId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                resultSet.add(new ProductTbl(
                        rs.getLong(ProductTbl.PRODUCT_ID_COLUMN),
                        rs.getLong(ProductTbl.CATEGORY_ID_COLUMN),
                        rs.getString(ProductTbl.TITLE_COLUMN),
                        rs.getInt(ProductTbl.COST_INTEGER_COLUMN),
                        rs.getInt(ProductTbl.COST_FRACTIONAL_COLUMN),
                        rs.getString(ProductTbl.TEXT_COLUMN)));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException ", e.getMessage());
        }
        return resultSet;
    }

    @Override
    public ProductTbl findById(Long newsId) {
        ProductTbl productTbl = null;
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, newsId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                productTbl = new ProductTbl(
                        rs.getLong(ProductTbl.PRODUCT_ID_COLUMN),
                        rs.getLong(ProductTbl.CATEGORY_ID_COLUMN),
                        rs.getString(ProductTbl.TITLE_COLUMN),
                        rs.getInt(ProductTbl.COST_INTEGER_COLUMN),
                        rs.getInt(ProductTbl.COST_FRACTIONAL_COLUMN),
                        rs.getString(ProductTbl.TEXT_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return productTbl;
    }

    @Override
    public ProductTbl findByTitle(String title) {
        ProductTbl productTbl = null;
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_TITLE);
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                productTbl = new ProductTbl(
                        rs.getLong(ProductTbl.PRODUCT_ID_COLUMN),
                        rs.getLong(ProductTbl.CATEGORY_ID_COLUMN),
                        rs.getString(ProductTbl.TITLE_COLUMN),
                        rs.getInt(ProductTbl.COST_INTEGER_COLUMN),
                        rs.getInt(ProductTbl.COST_FRACTIONAL_COLUMN),
                        rs.getString(ProductTbl.TEXT_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return productTbl;
    }

    @Override
    public void insert(ProductTbl productTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, productTbl.getCategoryId());
            statement.setString(2, productTbl.getTitle());
            statement.setInt(3, productTbl.getCostInteger());
            statement.setInt(4, productTbl.getCostFractional());
            statement.setString(5, productTbl.getText());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void update(ProductTbl productTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setLong(1, productTbl.getCategoryId());
            statement.setString(2, productTbl.getTitle());
            statement.setInt(3, productTbl.getCostInteger());
            statement.setInt(4, productTbl.getCostFractional());
            statement.setString(5, productTbl.getText());
            statement.setLong(6, productTbl.getProductId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void delete(ProductTbl productTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, productTbl.getProductId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }
}
