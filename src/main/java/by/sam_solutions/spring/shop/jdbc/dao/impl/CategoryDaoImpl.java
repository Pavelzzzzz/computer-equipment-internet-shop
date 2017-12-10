package by.sam_solutions.spring.shop.jdbc.dao.impl;

import by.sam_solutions.spring.shop.jdbc.dao.CategoryDao;
import by.sam_solutions.spring.shop.jdbc.model.CategoryTbl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

public class CategoryDaoImpl implements CategoryDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDaoImpl.class);

    public static final String SQL_GET_ALL_CATEGORY = "SELECT * FROM " +
            CategoryTbl.TABLE_NAME + " ORDER BY " + CategoryTbl.CATEGORY_COLUMN;
    public static final String SQL_FIND_BY_ID = "SELECT * FROM " +
            CategoryTbl.TABLE_NAME + " WHERE " + CategoryTbl.CATEGORY_ID_COLUMN + " = ?";
    public static final String SQL_FIND_BY_CATEGORY = "SELECT * FROM " +
            CategoryTbl.TABLE_NAME + " WHERE " + CategoryTbl.CATEGORY_COLUMN + " = ?";
    public static final String SQL_INSERT = "INSERT INTO " +
            CategoryTbl.TABLE_NAME + " (" + CategoryTbl.CATEGORY_COLUMN + ") VALUES(?)";
    public static final String SQL_UPDATE = "UPDATE " + CategoryTbl.TABLE_NAME +
            " SET " + CategoryTbl.CATEGORY_COLUMN + " = ? WHERE " +
            CategoryTbl.CATEGORY_ID_COLUMN + " = ?";
    public static final String SQL_DELETE = "DELETE FROM " + CategoryTbl.TABLE_NAME +
            " WHERE " + CategoryTbl.CATEGORY_ID_COLUMN + " = ?";

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
    public Set<CategoryTbl> getAllCategory(){
        Set<CategoryTbl> resultsSet =  new LinkedHashSet<CategoryTbl>();
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement(SQL_GET_ALL_CATEGORY);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                CategoryTbl currentCategoryTbl = new CategoryTbl(
                        rs.getLong(CategoryTbl.CATEGORY_ID_COLUMN), 
                        rs.getString(CategoryTbl.CATEGORY_COLUMN));
                resultsSet.add(currentCategoryTbl);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return resultsSet;
    }

    @Override
    public CategoryTbl findById(Long categoryId) {
        CategoryTbl category = null;
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement =
                    connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, categoryId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                category = new CategoryTbl(rs.getLong(CategoryTbl.CATEGORY_ID_COLUMN),
                        rs.getString(CategoryTbl.CATEGORY_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return category;
    }

    @Override
    public CategoryTbl findByName(String categoryIn) {
        CategoryTbl category = null;
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_CATEGORY);
            statement.setString(1, categoryIn);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                category = new CategoryTbl(rs.getLong(CategoryTbl.CATEGORY_ID_COLUMN),
                        rs.getString(CategoryTbl.CATEGORY_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return category;
    }

    @Override
    public void insert(CategoryTbl categoryTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, categoryTbl.getCategory());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void update(CategoryTbl categoryTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, categoryTbl.getCategory());
            statement.setLong(2, categoryTbl.getCategoryId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void delete(CategoryTbl categoryTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, categoryTbl.getCategoryId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }
}
