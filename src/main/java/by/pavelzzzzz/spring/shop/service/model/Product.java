package by.pavelzzzzz.spring.shop.service.model;

public class Product {

    private Long productId;
    private Long categoryId;
    private String title;
    private int costInteger;
    private int costFractional;
    private String text;;

    public Product() {
    }

    public Product(Long productId, Long categoryId, String title, int costInteger, int costFractional, String text) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.title = title;
        this.costInteger = costInteger;
        this.costFractional = costFractional;
        this.text = text;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCostInteger() {
        return costInteger;
    }

    public void setCostInteger(int costInteger) {
        this.costInteger = costInteger;
    }

    public int getCostFractional() {
        return costFractional;
    }

    public void setCostFractional(int costFractional) {
        this.costFractional = costFractional;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", costInteger=" + costInteger +
                ", costFractional=" + costFractional +
                ", text='" + text + '\'' +
                '}';
    }
}
