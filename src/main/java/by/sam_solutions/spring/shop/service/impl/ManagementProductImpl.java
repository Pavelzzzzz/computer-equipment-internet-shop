package by.sam_solutions.spring.shop.service.impl;

import by.sam_solutions.spring.shop.exception.ServiceException;
import by.sam_solutions.spring.shop.jdbc.dao.ProductDao;
import by.sam_solutions.spring.shop.jdbc.model.CategoryTbl;
import by.sam_solutions.spring.shop.jdbc.model.ProductTbl;
import by.sam_solutions.spring.shop.service.ManagementProduct;
import by.sam_solutions.spring.shop.service.model.Category;
import by.sam_solutions.spring.shop.service.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ManagementProductImpl implements ManagementProduct {

    @Autowired
    private ProductDao productDao;

    @Override
    public Set<Product> getAllProducts(){
        Set<Product> resultSet = new LinkedHashSet<Product>();
        Iterator<ProductTbl> iterator =
                productDao.getAllProducts().iterator();
        while (iterator.hasNext()){
            ProductTbl foundProduct = iterator.next();
            resultSet.add(new Product(
                    foundProduct.getProductId(),
                    foundProduct.getCategoryId(),
                    foundProduct.getTitle(),
                    foundProduct.getCostInteger(),
                    foundProduct.getCostFractional(),
                    foundProduct.getText()));
        }
        return resultSet;
    }

    @Override
    public Set<Product> getAllProductsByCategory(Category category){
        Set<Product> resultSet = new LinkedHashSet<Product>();
        Iterator<ProductTbl> iterator =
                productDao.getAllProductsByCategory( new CategoryTbl(
                        category.getCategoryId(), category.getCategory())
                ).iterator();
        while (iterator.hasNext()){
            ProductTbl foundProduct = iterator.next();
            resultSet.add(new Product(
                    foundProduct.getProductId(),
                    foundProduct.getCategoryId(),
                    foundProduct.getTitle(),
                    foundProduct.getCostInteger(),
                    foundProduct.getCostFractional(),
                    foundProduct.getText()));
        }
        return resultSet;
    }

    @Override
    public int addProduct (Long categoryId, String title, int costInteger, int costFractional,  String text) throws ServiceException {

        if  (productDao.findByTitle(title) != null) {
            throw new ServiceException("This title already exists: " + title);
        }
        productDao.insert(new ProductTbl(
                null, categoryId, title, costInteger, costFractional, text));
        return 1;
    }

    @Override
    public Product findProductById(Long productId) throws ServiceException {
        ProductTbl foundProduct = productDao.findById(productId);

        if  ( foundProduct == null) {
            throw new ServiceException("This product is not found");
        }

        Product product = new Product(
                foundProduct.getProductId(),
                foundProduct.getCategoryId(),
                foundProduct.getTitle(),
                foundProduct.getCostInteger(),
                foundProduct.getCostFractional(),
                foundProduct.getText()
        );

        return product;
    }

    @Override
    public Product findProductByTitle(String title) throws ServiceException {
        ProductTbl foundProduct = productDao.findByTitle(title);

        if  ( foundProduct == null) {
            throw new ServiceException("This product is not found");
        }

        Product product = new Product(
                foundProduct.getProductId(),
                foundProduct.getCategoryId(),
                foundProduct.getTitle(),
                foundProduct.getCostInteger(),
                foundProduct.getCostFractional(),
                foundProduct.getText()
        );

        return product;
    }

    @Override
    public int updateProduct(Product product) throws ServiceException {
        ProductTbl foundProduct = productDao.findById(product.getProductId());

        if  ( foundProduct == null) {
            throw new ServiceException("This product is not found");
        }

        if  ((productDao.findByTitle(product.getTitle()) != null) &
                (productDao.findByTitle(product.getTitle()).getProductId() != product.getProductId())){
            throw new ServiceException("This title already exists: " + product.getTitle());
        }

        foundProduct.setCategoryId(product.getCategoryId());
        foundProduct.setTitle(product.getTitle());
        foundProduct.setText(product.getText());

        productDao.update(foundProduct);

        return 1;
    }

    @Override
    public int deleteProductById (Long productId) throws ServiceException {
        ProductTbl foundProduct = productDao.findById(productId);

        if  ( foundProduct == null) {
            throw new ServiceException("This product is not found");
        }

        productDao.delete(foundProduct);

        return 1;
    }
}
