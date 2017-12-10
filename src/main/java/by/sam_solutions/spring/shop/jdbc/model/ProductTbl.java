package by.sam_solutions.spring.shop.jdbc.model;

/**
 * POJO class for tblSECProduct
 */

public class ProductTbl {
    public static final String TABLE_NAME = "tblSECProduct";
    public static final String PRODUCT_ID_COLUMN = "ProductId";
    public static final String CATEGORY_ID_COLUMN = "CategoryId";
    public static final String TITLE_COLUMN = "Title";
    public static final String COST_INTEGER_COLUMN = "CostInteger";
    public static final String COST_FRACTIONAL_COLUMN = "CostFractional";
    public static final String TEXT_COLUMN = "Text";

    private Long ProductId;
    private Long CategoryId;
    private String Title;
    private int CostInteger;
    private int CostFractional;
    private String Text;

    public ProductTbl(Long productId, Long categoryId, String title, int costInteger, int costFractional, String text) {
        ProductId = productId;
        CategoryId = categoryId;
        Title = title;
        CostInteger = costInteger;
        CostFractional = costFractional;
        Text = text;
    }

    public ProductTbl() {
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

    public Long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Long categoryId) {
        CategoryId = categoryId;
    }

    public int getCostInteger() {
        return CostInteger;
    }

    public void setCostInteger(int costInteger) {
        CostInteger = costInteger;
    }

    public int getCostFractional() {
        return CostFractional;
    }

    public void setCostFractional(int costFractional) {
        CostFractional = costFractional;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    @Override
    public String toString() {
        return "ProductTbl{" +
                "ProductId=" + ProductId +
                ", CategoryId=" + CategoryId +
                ", Title='" + Title + '\'' +
                ", CostInteger=" + CostInteger +
                ", CostFractional=" + CostFractional +
                ", Text='" + Text + '\'' +
                '}';
    }
}