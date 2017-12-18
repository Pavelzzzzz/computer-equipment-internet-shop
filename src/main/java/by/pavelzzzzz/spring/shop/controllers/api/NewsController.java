package by.pavelzzzzz.spring.shop.controllers.api;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.service.ManagementProduct;
import by.pavelzzzzz.spring.shop.service.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class NewsController {

    @Autowired
    private ManagementProduct managementProduct;

    @GetMapping("/{productId}")
    public Product findProductById(@PathVariable Long productId) throws ServiceException {
        return managementProduct.findProductById(productId);
    }

    @GetMapping()
    public Set<Product> getAllProducts(){
        return managementProduct.getAllProducts();
    }

    @PostMapping()
    public Product addNewProduct(Long categoryId, String title, int costInteger, int costFractional, String text) throws ServiceException {
        managementProduct.addProduct(categoryId, title, costInteger, costFractional, text);
        return managementProduct.findProductByTitle(title);
    }

    @DeleteMapping("/{productsId}")
    public void deleteNewsById(@PathVariable Long productId) throws ServiceException {
        managementProduct.deleteProductById(productId);
    }
}
