package by.pavelzzzzz.spring.shop.jdbc.dao.impl;

import by.pavelzzzzz.spring.shop.jdbc.dao.RoleDao;
import by.pavelzzzzz.spring.shop.jdbc.model.RoleTbl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class RoleDaoImpl implements RoleDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDaoImpl.class);

    public static final String SQL_GET_ALL_ROLES = "SELECT * FROM " +
            RoleTbl.TABLE_NAME;
    public static final String SQL_FIND_BY_ID = "SELECT * FROM " +
            RoleTbl.TABLE_NAME + " WHERE " + RoleTbl.ROLE_ID_COLUMN + " = ?";
    public static final String SQL_FIND_BY_ROLE_NAME = "SELECT * FROM " +
            RoleTbl.TABLE_NAME + " WHERE " + RoleTbl.ROLE_COLUMN + " = ?";
    public static final String SQL_INSERT = "INSERT INTO " +
            RoleTbl.TABLE_NAME + " (" + RoleTbl.ROLE_COLUMN + ") VALUES(?)";
    public static final String SQL_UPDATE = "UPDATE " + RoleTbl.TABLE_NAME +
            " SET " + RoleTbl.ROLE_COLUMN + " = ? WHERE " +
            RoleTbl.ROLE_ID_COLUMN + " = ?";
    public static final String SQL_DELETE = "DELETE FROM " + RoleTbl.TABLE_NAME +
            " WHERE " + RoleTbl.ROLE_ID_COLUMN + " = ?";

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
    public Set<RoleTbl> getAllRoles(){
        Set<RoleTbl> resultsSet =  new HashSet<RoleTbl>();
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement(SQL_GET_ALL_ROLES);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                RoleTbl currentRoleTbl = new RoleTbl();
                currentRoleTbl.setRoleId(rs.getLong(RoleTbl.ROLE_ID_COLUMN));
                currentRoleTbl.setRole(rs.getString(RoleTbl.ROLE_COLUMN));
                resultsSet.add(currentRoleTbl);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return resultsSet;
    }

    @Override
    public RoleTbl findById(Long roleId) {
        RoleTbl role = null;
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement =
                    connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, roleId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                role = new RoleTbl();
                role.setRoleId(rs.getLong(RoleTbl.ROLE_ID_COLUMN));
                role.setRole(rs.getString(RoleTbl.ROLE_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return role;
    }

    @Override
    public RoleTbl findByName(String roleName) {
        RoleTbl role = null;
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ROLE_NAME);
            statement.setString(1, roleName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                role = new RoleTbl();
                role.setRoleId(rs.getLong(RoleTbl.ROLE_ID_COLUMN));
                role.setRole(rs.getString(RoleTbl.ROLE_COLUMN));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
        return role;
    }

    @Override
    public void insert(RoleTbl roleTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, roleTbl.getRole());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void update(RoleTbl roleTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, roleTbl.getRole());
            statement.setLong(2, roleTbl.getRoleId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }

    @Override
    public void delete(RoleTbl roleTbl) {
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, roleTbl.getRoleId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQLException", e.getMessage());
        }
    }
}
