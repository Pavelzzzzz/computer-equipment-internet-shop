package by.sam_solutions.spring.shop.service.impl;

import by.sam_solutions.spring.shop.exception.ServiceException;
import by.sam_solutions.spring.shop.jdbc.dao.CategoryDao;
import by.sam_solutions.spring.shop.jdbc.model.CategoryTbl;
import by.sam_solutions.spring.shop.service.ManagementCategory;
import by.sam_solutions.spring.shop.service.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ManagementCategoryImpl implements ManagementCategory {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public int addCategory(String category) throws ServiceException {

        if (categoryDao.findByName(category) != null){
            throw  new ServiceException("This category already exists");
        }

        categoryDao.insert(new CategoryTbl(null, category));
        return 1;
    }

    @Override
    public Set<Category> getAllCategory(){
        Set<Category> resultSet = new LinkedHashSet<>();
        Iterator<CategoryTbl> iterator = categoryDao.getAllCategory().iterator();
        while (iterator.hasNext()){
            CategoryTbl currentCategoryTbl = iterator.next();
            resultSet.add(new Category(
                    currentCategoryTbl.getCategoryId(),
                    currentCategoryTbl.getCategory()));
        }
        return resultSet;
    }

    @Override
    public Category findCategoryById(Long categoryId) throws ServiceException {

        CategoryTbl foundCategory = categoryDao.findById(categoryId);

        if (foundCategory == null){
            throw new ServiceException("This category is not found");
        }

        Category category = new Category(foundCategory.getCategoryId(),
                foundCategory.getCategory());

        return category;
    }

    @Override
    public Category findCategoryByName(String categoryName) throws ServiceException{
        CategoryTbl foundCategory = categoryDao.findByName(categoryName);

        if (foundCategory == null){
            throw new ServiceException("This category is not found");
        }

        Category category = new Category(foundCategory.getCategoryId(),
                foundCategory.getCategory());

        return category;
    }

    @Override
    public int updateCategory(Long categoryId, String categoryName) throws ServiceException {
        CategoryTbl foundCategory = categoryDao.findById(categoryId);

        if (foundCategory == null){
            throw new ServiceException("This category is not found");
        }

        if (categoryDao.findByName(categoryName) != null){
            throw  new ServiceException("This category already exists");
        }

        foundCategory.setCategory(categoryName);

        categoryDao.update(foundCategory);

        return 1;
    }

    @Override
    public int deleteCategory(Long categoryId) throws ServiceException{
        CategoryTbl foundCategory = categoryDao.findById(categoryId);

        if( foundCategory == null){
            throw new ServiceException("This category is not found");
        }

        categoryDao.delete(foundCategory);

        return 1;
    }
}
