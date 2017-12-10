package by.sam_solutions.spring.shop.jdbc.dao.impl;

import by.sam_solutions.spring.shop.jdbc.dao.PasswordDao;
import by.sam_solutions.spring.shop.jdbc.model.PasswordTbl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PasswordDaoImpl implements PasswordDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordDaoImpl.class);

    public static final String SQL_FIND_BY_USER_ID = "SELECT * FROM " +
            PasswordTbl.TABLE_NAME + " WHERE " + PasswordTbl.USER_ID_COLUMN + " = ?";
    public static final String SQL_INSERT = "INSERT INTO " +
            PasswordTbl.TABLE_NAME + " (" + PasswordTbl.USER_ID_COLUMN + ", " +
            PasswordTbl.PASSWORD_COLUMN + ") VALUES(?, ?)";
    public static final String SQL_UPDATE = "UPDATE " + PasswordTbl.TABLE_NAME +
            " SET " + PasswordTbl.PASSWORD_COLUMN + " = ? WHERE " +
            PasswordTbl.USER_ID_COLUMN + " = ?";
    public static final String SQL_DELETE = "DELETE FROM " + PasswordTbl.TABLE_NAME +
            " WHERE " + PasswordTbl.USER_ID_COLUMN + " = ?";

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
    public PasswordTbl findByUserId(Long userId) {
        PasswordTbl passwordTbl = null;
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID);
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                passwordTbl = new PasswordTbl();
                passwordTbl.setUserId(rs.getLong(PasswordTbl.USER_ID_COLUMN));
                passwordTbl.setPassword(rs.getString(PasswordTbl.PASSWORD_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return passwordTbl;
    }

    @Override
    public void insert(PasswordTbl passwordTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, passwordTbl.getUserId());
            statement.setString(2, passwordTbl.getPassword());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void update(PasswordTbl passwordTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, passwordTbl.getPassword());
            statement.setLong(2, passwordTbl.getUserId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void delete(PasswordTbl passwordTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, passwordTbl.getUserId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }
}
