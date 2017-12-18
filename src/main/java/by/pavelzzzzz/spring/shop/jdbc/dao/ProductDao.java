package by.pavelzzzzz.spring.shop.jdbc.dao;

import by.pavelzzzzz.spring.shop.jdbc.model.CategoryTbl;
import by.pavelzzzzz.spring.shop.jdbc.model.ProductTbl;

import javax.sql.DataSource;
import java.util.Set;

public interface ProductDao {

    DataSource getDataSource();

    Set<ProductTbl> getAllProducts();

    Set<ProductTbl> getAllProductsByCategory(CategoryTbl categoryTbl);

    ProductTbl findById(Long productId);

    ProductTbl findByTitle(String title);

    void insert(ProductTbl productTbl);

    void update(ProductTbl productTbl);

    void delete(ProductTbl productTbl);
}
