package by.pavelzzzzz.spring.shop.service;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.service.model.Category;
import by.pavelzzzzz.spring.shop.service.model.Product;

import java.util.Set;

public interface ManagementProduct {

    Set<Product> getAllProducts();

    Set<Product> getAllProductsByCategory(Category category);

    int addProduct(Long categoryId, String title, int costInteger, int costFractional,  String text) throws ServiceException;

    Product findProductById(Long productId) throws ServiceException;

    Product findProductByTitle(String title) throws ServiceException;

    int updateProduct(Product product) throws ServiceException;

    int deleteProductById(Long productId) throws ServiceException;
}
