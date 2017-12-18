package by.pavelzzzzz.spring.shop.jdbc.dao;

import by.pavelzzzzz.spring.shop.jdbc.model.PasswordTbl;

import javax.sql.DataSource;

public interface PasswordDao {

    DataSource getDataSource();

    PasswordTbl findByUserId(Long userId);

    void insert(PasswordTbl passwordTbl);

    void update(PasswordTbl passwordTbl);

    void delete(PasswordTbl passwordTbl);
}
