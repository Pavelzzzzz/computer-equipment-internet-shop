package by.pavelzzzzz.spring.shop.controllers.api;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.service.ManagementCategory;
import by.pavelzzzzz.spring.shop.service.ManagementProduct;
import by.pavelzzzzz.spring.shop.service.model.Category;
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
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private ManagementCategory managementCategory;

    @Autowired
    private ManagementProduct managementProduct;

    @GetMapping("/{categoryId}")
    public Category findCategoryById(@PathVariable Long categoryId) throws ServiceException {
        return managementCategory.findCategoryById(categoryId);
    }

    @GetMapping("/{categoryId}/products")
    public Set<Product> findProductsByCategoryId(@PathVariable Long categoryId) throws ServiceException {
        return managementProduct.getAllProductsByCategory(managementCategory.findCategoryById(categoryId));
    }

    @GetMapping()
    public Set<Category> getAllCategory(){
        return managementCategory.getAllCategory();
    }

    @PostMapping()
    public Category addNewCategory(String categoryName) throws ServiceException {
        managementCategory.addCategory(categoryName);
        return managementCategory.findCategoryByName(categoryName);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategoryById(@PathVariable Long categoryId) throws ServiceException {
        managementCategory.deleteCategory(categoryId);
    }
}
