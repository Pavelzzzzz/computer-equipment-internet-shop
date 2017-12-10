package by.sam_solutions.spring.shop.jdbc.model;

/**
 * POJO class for tblSECCategory
 */

public class CategoryTbl {
    public static final String TABLE_NAME = "tblSECCategory";
    public static final String CATEGORY_ID_COLUMN = "CategoryId";
    public static final String CATEGORY_COLUMN = "Category";

    private Long categoryId;
    private String category;

    public CategoryTbl() {
    }

    public CategoryTbl(Long categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryTbl{" +
                "categoryId=" + categoryId +
                ", category='" + category + '\'' +
                '}';
    }
}
