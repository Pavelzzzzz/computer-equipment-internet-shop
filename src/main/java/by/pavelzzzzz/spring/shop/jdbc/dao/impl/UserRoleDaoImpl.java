package by.pavelzzzzz.spring.shop.jdbc.dao.impl;

import by.pavelzzzzz.spring.shop.jdbc.dao.UserRoleDao;
import by.pavelzzzzz.spring.shop.jdbc.model.UserRoleTbl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserRoleDaoImpl implements UserRoleDao{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleDaoImpl.class);

    public static final String SQL_FIND_ALL_ROLE = "SELECT * FROM " +
            UserRoleTbl.TABLE_NAME + " WHERE " + UserRoleTbl.USER_ID_COLUMN + " = ?;";
    public static final String SQL_IS_EXIST_OBJECT = "SELECT * FROM " +
            UserRoleTbl.TABLE_NAME + " WHERE " + UserRoleTbl.USER_ID_COLUMN + " = ? AND " +
            UserRoleTbl.ROLE_ID_COLUMN + " = ?;";
    public static final String SQL_INSERT = "INSERT INTO " +
            UserRoleTbl.TABLE_NAME + " (" + UserRoleTbl.USER_ID_COLUMN + ", " +
            UserRoleTbl.ROLE_ID_COLUMN + ") VALUES(?, ?);";
    public static final String SQL_DELETE = "DELETE FROM " + UserRoleTbl.TABLE_NAME +
            " WHERE " + UserRoleTbl.USER_ID_COLUMN + " = ? AND " + UserRoleTbl.ROLE_ID_COLUMN + " = ?;";

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
    public Set<UserRoleTbl> getAllRoles(Long userId){
        Set<UserRoleTbl> resultSet = new HashSet<UserRoleTbl>();
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ROLE);
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                UserRoleTbl currentUserRoleTbl = new UserRoleTbl();
                currentUserRoleTbl.setUserId(rs.getLong(UserRoleTbl.USER_ID_COLUMN));
                currentUserRoleTbl.setRoleId(rs.getLong(UserRoleTbl.ROLE_ID_COLUMN));
                resultSet.add(currentUserRoleTbl);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException ", e.getMessage());
        }
        return resultSet;
    }

    @Override
    public boolean isExistObject(UserRoleTbl userRoleTbl){
        UserRoleTbl foundUserRoleTbl = null;
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_IS_EXIST_OBJECT);
            statement.setLong(1, userRoleTbl.getUserId());
            statement.setLong(2, userRoleTbl.getRoleId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                foundUserRoleTbl = new UserRoleTbl();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException ", e.getMessage());
        }
        return (foundUserRoleTbl != null) ? true : false;
    }

    @Override
    public void insert(UserRoleTbl userRoleTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, userRoleTbl.getUserId());
            statement.setLong(2, userRoleTbl.getRoleId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException ", e.getMessage());
        }
    }

    @Override
    public void delete(UserRoleTbl userRoleTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, userRoleTbl.getUserId());
            statement.setLong(2, userRoleTbl.getRoleId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException ", e.getMessage());
        }
    }
}
