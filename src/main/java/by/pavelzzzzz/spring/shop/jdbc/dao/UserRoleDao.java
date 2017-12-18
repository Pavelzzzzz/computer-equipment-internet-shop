package by.pavelzzzzz.spring.shop.jdbc.dao;

import by.pavelzzzzz.spring.shop.jdbc.model.UserRoleTbl;

import javax.sql.DataSource;
import java.util.Set;

public interface UserRoleDao {

    DataSource getDataSource();

    Set<UserRoleTbl> getAllRoles(Long userId);

    boolean isExistObject(UserRoleTbl userRoleTbl);

    void insert(UserRoleTbl userRoleTbl);

    void delete(UserRoleTbl userRoleTbl);
}
