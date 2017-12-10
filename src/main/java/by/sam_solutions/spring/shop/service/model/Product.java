package by.sam_solutions.spring.shop.service.model;

public class Product {

    private Long ProductId;
    private Long CategoryId;
    private String Title;
    private int CostInteger;
    private int CostFractional;
    private String Text;;

    public Product() {
    }

    public Product(Long productId, Long categoryId, String title, int costInteger, int costFractional, String text) {
        ProductId = productId;
        CategoryId = categoryId;
        Title = title;
        CostInteger = costInteger;
        CostFractional = costFractional;
        Text = text;
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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductId=" + ProductId +
                ", CategoryId=" + CategoryId +
                ", Title='" + Title + '\'' +
                ", CostInteger=" + CostInteger +
                ", CostFractional=" + CostFractional +
                ", Text='" + Text + '\'' +
                '}';
    }
}
