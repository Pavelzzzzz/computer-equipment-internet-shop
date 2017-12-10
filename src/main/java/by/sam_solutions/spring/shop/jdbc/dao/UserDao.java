package by.sam_solutions.spring.shop.jdbc.dao;

import by.sam_solutions.spring.shop.jdbc.model.UserTbl;

import javax.sql.DataSource;
import java.util.Set;

public interface UserDao {

    DataSource getDataSource();

    Set<UserTbl> getAllUsers();

    UserTbl findById(Long userId);

    UserTbl findByLogin (String login);

    void insert (UserTbl userTbl);

    void update (UserTbl userTbl);

    void delete (UserTbl userTbl);
}
