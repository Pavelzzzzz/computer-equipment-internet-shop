package by.sam_solutions.spring.shop.jdbc.dao.impl;

import by.sam_solutions.spring.shop.jdbc.dao.UserDao;
import by.sam_solutions.spring.shop.jdbc.model.UserTbl;
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
import java.util.HashSet;
import java.util.Set;

@Component
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    public static final String SQL_GET_ALL_USER = "SELECT * FROM " +
            UserTbl.TABLE_NAME + " ORDER BY " + UserTbl.LOGIN_COLUMN;
    public static final String SQL_FIND_BY_ID = "SELECT * FROM " +
            UserTbl.TABLE_NAME + " WHERE " + UserTbl.USER_ID_COLUMN + " = ?";
    public static final String SQL_FIND_BY_LOGIN = "SELECT * FROM " +
            UserTbl.TABLE_NAME + " WHERE " + UserTbl.LOGIN_COLUMN + " = ?";
    public static final String SQL_INSERT = "INSERT INTO " +
            UserTbl.TABLE_NAME + " (" + UserTbl.LOGIN_COLUMN + ", " +
            UserTbl.EMAIL_COLUMN + ", " + UserTbl.IS_ACTIVE_COLUMN + ") VALUES(?, ?, ?)";
    public static final String SQL_UPDATE = "UPDATE " + UserTbl.TABLE_NAME +
            " SET " + UserTbl.LOGIN_COLUMN + " = ?, " +
            UserTbl.EMAIL_COLUMN + " = ?, " +
            UserTbl.IS_ACTIVE_COLUMN + " = ? WHERE " +
            UserTbl.USER_ID_COLUMN + " = ?";
    public static final String SQL_DELETE = "DELETE FROM " + UserTbl.TABLE_NAME +
            " WHERE " + UserTbl.USER_ID_COLUMN + " = ?";

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
    public Set<UserTbl> getAllUsers(){
        Set<UserTbl> resultSet = new HashSet<UserTbl>();
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_USER);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                UserTbl currentUserTbl = new UserTbl();
                currentUserTbl.setUserId(rs.getLong(UserTbl.USER_ID_COLUMN));
                currentUserTbl.setLogin(rs.getString(UserTbl.LOGIN_COLUMN));
                currentUserTbl.setEmail(rs.getString(UserTbl.EMAIL_COLUMN));
                currentUserTbl.setActive(rs.getBoolean(UserTbl.IS_ACTIVE_COLUMN));
                resultSet.add(currentUserTbl);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException ", e.getMessage());
        }
        return resultSet;
    }

    @Override
    public UserTbl findById(Long userId) {
        UserTbl userTbl = null;
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                userTbl = new UserTbl();
                userTbl.setUserId(rs.getLong(UserTbl.USER_ID_COLUMN));
                userTbl.setLogin(rs.getString(UserTbl.LOGIN_COLUMN));
                userTbl.setEmail(rs.getString(UserTbl.EMAIL_COLUMN));
                userTbl.setActive(rs.getBoolean(UserTbl.IS_ACTIVE_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return userTbl;
    }

    @Override
    public UserTbl findByLogin(String login) {
        UserTbl userTbl = null;
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                userTbl = new UserTbl();
                userTbl.setUserId(rs.getLong(UserTbl.USER_ID_COLUMN));
                userTbl.setLogin(rs.getString(UserTbl.LOGIN_COLUMN));
                userTbl.setEmail(rs.getString(UserTbl.EMAIL_COLUMN));
                userTbl.setActive(rs.getBoolean(UserTbl.IS_ACTIVE_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return userTbl;
    }

    @Override
    public void insert(UserTbl userTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userTbl.getLogin());
            statement.setString(2, userTbl.getEmail());
            statement.setBoolean(3, userTbl.isActive());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void update(UserTbl userTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, userTbl.getLogin());
            statement.setString(2, userTbl.getEmail());
            statement.setBoolean(3, userTbl.isActive());
            statement.setLong(4, userTbl.getUserId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void delete(UserTbl userTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, userTbl.getUserId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());

        }
    }
}
