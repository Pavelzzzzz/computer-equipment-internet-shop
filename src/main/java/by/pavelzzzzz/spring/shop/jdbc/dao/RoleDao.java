package by.pavelzzzzz.spring.shop.jdbc.dao;

import by.pavelzzzzz.spring.shop.jdbc.model.RoleTbl;

import javax.sql.DataSource;
import java.util.Set;

public interface RoleDao {

    DataSource getDataSource();

    Set<RoleTbl> getAllRoles();

    RoleTbl findById(Long roleId);

    void insert (RoleTbl roleTbl);

    void update (RoleTbl roleTbl);

    RoleTbl findByName(String roleName);

    void delete (RoleTbl roleTbl);
}
