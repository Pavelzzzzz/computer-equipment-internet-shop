package by.pavelzzzzz.spring.shop.service.impl;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.jdbc.dao.CategoryDao;
import by.pavelzzzzz.spring.shop.jdbc.model.CategoryTbl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ManagementCategoryImplTest {
    
    @Mock
    private CategoryDao categoryDao;
    
    @InjectMocks
    private ManagementCategoryImpl managementCategory;
    
    @Test
    public void addCategory() {
        when(categoryDao.findByName(anyString())).thenReturn(null);
        doNothing().when(categoryDao).insert(any(CategoryTbl.class));

        try {
            assertEquals(1,
                    managementCategory.addCategory("TestCategory"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void addCategoryThisCategoryAlreadyExists() {
        when(categoryDao.findByName(anyString())).thenReturn(new CategoryTbl());

        try {
            managementCategory.addCategory("TestCategory");
        } catch (ServiceException e) {
            assertEquals("This category already exists",
                    e.getMessage());
        }
    }

    @Test
    public void getAllCategory() {
        when(categoryDao.getAllCategory()).thenReturn(new LinkedHashSet<>());
        assertEquals(0,
                managementCategory.getAllCategory().size());
    }

    @Test
    public void findCategoryById() {
        when(categoryDao.findById(anyLong())).thenReturn( new CategoryTbl(
                123L,
                "TestCategory"));

        try {
            assertEquals("TestCategory",
                    managementCategory.findCategoryById(123L).getCategory());
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void findCategoryByIdThisCategoryIsNotFound() {
        when(categoryDao.findById(anyLong())).thenReturn(null);

        try {
            managementCategory.findCategoryById(123L);
        } catch (ServiceException e) {
            assertEquals("This category is not found",
                    e.getMessage());
        }
    }

    @Test
    public void findCategoryByName() {
        when(categoryDao.findByName(anyString())).thenReturn(new CategoryTbl(
                123L,
                "TestCategory"));
        try {
            assertEquals("TestCategory",
                    managementCategory.findCategoryByName("TestCategory").getCategory());
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void findCategoryByNameThisCategoryIsNotFound() {
        when(categoryDao.findById(anyLong())).thenReturn(null);

        try {
            managementCategory.findCategoryByName("TestCategory");
        } catch (ServiceException e) {
            assertEquals("This category is not found",
                    e.getMessage());
        }
    }

    @Test
    public void updateCategory() {
        when(categoryDao.findById(anyLong())).thenReturn(new CategoryTbl());
        when(categoryDao.findByName(anyString())).thenReturn(null);
        doNothing().when(categoryDao).update(any(CategoryTbl.class));

        try {
            assertEquals(1,
                    managementCategory.updateCategory(123L, "TestCategory"));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void updateCategoryThisCategoryIsNotFound() {
        when(categoryDao.findById(anyLong())).thenReturn(null);
        when(categoryDao.findByName(anyString())).thenReturn(null);

        try {
            managementCategory.updateCategory(123L, "TestCategory");
        } catch (ServiceException e) {
            assertEquals("This category is not found",
                    e.getMessage());
        }
    }

    @Test
    public void updateCategoryThisCategoryAlreadyExists() {
        when(categoryDao.findById(anyLong())).thenReturn(new CategoryTbl());
        when(categoryDao.findByName(anyString())).thenReturn(new CategoryTbl());

        try {
            managementCategory.updateCategory(123L, "TestCategory");
        } catch (ServiceException e) {
            assertEquals("This category already exists",
                    e.getMessage());
        }
    }

    @Test
    public void deleteCategory() {
        when(categoryDao.findById(anyLong())).thenReturn(new CategoryTbl());
        doNothing().when(categoryDao).delete(any(CategoryTbl.class));

        try {
            assertEquals(1,
                    managementCategory.deleteCategory(123L));
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void deleteCategoryThisCategoryIsNotFound() {
        when(categoryDao.findById(anyLong())).thenReturn(null);

        try {
            managementCategory.deleteCategory(123L);
        } catch (ServiceException e) {
            assertEquals("This category is not found",
                    e.getMessage());
        }
    }


}