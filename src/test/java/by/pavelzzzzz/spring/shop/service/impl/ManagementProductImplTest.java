package by.pavelzzzzz.spring.shop.service.impl;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.jdbc.dao.ProductDao;
import by.pavelzzzzz.spring.shop.jdbc.model.CategoryTbl;
import by.pavelzzzzz.spring.shop.jdbc.model.ProductTbl;
import by.pavelzzzzz.spring.shop.service.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ManagementProductImplTest {
    
    @Mock
    private ProductDao productDao;
    
    @InjectMocks
    private ManagementProductImpl managementProduct;
    
    @Test
    public void getAllProducts() {
        when(productDao.getAllProducts()).thenReturn(new LinkedHashSet<>());

        assertEquals(0,
                managementProduct.getAllProducts().size());
    }

    @Test
    public void getAllProductsByCategory() {
        when(productDao.getAllProductsByCategory(any(CategoryTbl.class)))
                .thenReturn(new LinkedHashSet<>());

        assertEquals(0,
                managementProduct.getAllProducts().size());
    }

    @Test
    public void addProducts() {
        when(productDao.findByTitle(anyString())).thenReturn(null);
        doNothing().when(productDao).insert(any(ProductTbl.class));

        try {
            assertEquals(1,
                    managementProduct.addProduct(
                            1L, "Test title", 1, 15, "Some test text"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void addProductsThisTitleAlreadyExists() {
        when(productDao.findByTitle(anyString())).thenReturn(new ProductTbl());

        try {
            managementProduct.addProduct(
                            1L, "Test title", 1, 15, "Some test text");
        } catch (ServiceException e) {
            assertEquals("This title already exists: Test title",
                    e.getMessage());
        }
    }

    @Test
    public void findProductById() {
        when(productDao.findById(anyLong())).thenReturn(new ProductTbl());

        try {
            assertEquals(Product.class,
                    managementProduct.findProductById(123L).getClass());
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void findProductByIdThisProductIsNotFound() {
        when(productDao.findById(anyLong())).thenReturn(null);

        try {
            managementProduct.findProductById(123L);
        } catch (ServiceException e) {
            assertEquals("This product is not found",
                    e.getMessage());
        }
    }

    @Test
    public void findProductByTitle() {
        when(productDao.findByTitle(anyString())).thenReturn(new ProductTbl());

        try {
            assertEquals(Product.class,
                    managementProduct.findProductByTitle("Test title").getClass());
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void findProductByTitleThisProductIsNotFound() {
        when(productDao.findByTitle(anyString())).thenReturn(null);

        try {
            managementProduct.findProductByTitle("Test title");
        } catch (ServiceException e) {
            assertEquals("This product is not found",
                    e.getMessage());
        }
    }

    @Test
    public void updateProduct() {
        when(productDao.findById(anyLong())).thenReturn(new ProductTbl());
        when(productDao.findByTitle(anyString())).thenReturn(new ProductTbl());
        doNothing().when(productDao).update(any(null));

        try {
            assertEquals(1,
                    managementProduct.updateProduct(new Product()));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void updateProductThisProductIsNotFound() {
        when(productDao.findById(anyLong())).thenReturn(null);
        when(productDao.findByTitle(anyString())).thenReturn(null);

        try {
            managementProduct.updateProduct(new Product());
        } catch (ServiceException e) {
            assertEquals("This product is not found",
                    e.getMessage());
        }
    }

    @Test
    public void updateProductThisTitleAlreadyExists() {
        when(productDao.findById(anyLong())).thenReturn(new ProductTbl());
        when(productDao.findByTitle(anyString())).thenReturn(
                new ProductTbl(),
                new ProductTbl(123L, 123L, "Test title", 1, 15, null));

        try {
            managementProduct.updateProduct(
                    new Product(123L, 123L, "Test title", 1, 15, null));
        } catch (ServiceException e) {
            assertEquals("This title already exists: Test title",
                    e.getMessage());
        }
    }

    @Test
    public void deleteProductById() {
        when(productDao.findById(anyLong())).thenReturn(new ProductTbl());
        doNothing().when(productDao).delete(any(ProductTbl.class));

        try {
            assertEquals(1,
                    managementProduct.deleteProductById(123L));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void deleteProductByIdThisProductIsNotFound() {
        when(productDao.findById(anyLong())).thenReturn(null);

        try {
            managementProduct.deleteProductById(123L);
        } catch (ServiceException e) {
            assertEquals("This product is not found",
                    e.getMessage());
        }
    }

}