package by.pavelzzzzz.spring.shop.jdbc.dao;

import by.pavelzzzzz.spring.shop.jdbc.model.CategoryTbl;

import javax.sql.DataSource;
import java.util.Set;

public interface CategoryDao {

    DataSource getDataSource();

    Set<CategoryTbl> getAllCategory();

    CategoryTbl findById(Long categoryId);

    void insert(CategoryTbl categoryTbl);

    void update(CategoryTbl categoryTbl);

    CategoryTbl findByName(String category);

    void delete(CategoryTbl categoryTbl);
}
