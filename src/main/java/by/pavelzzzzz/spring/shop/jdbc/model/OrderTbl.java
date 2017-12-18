package by.pavelzzzzz.spring.shop.jdbc.model;

/**
 * POJO class for tblSECProduct
 */

public class OrderTbl {
    public static final String TABLE_NAME = "tblSECOrder";
    public static final String ORDER_ID_COLUMN = "OrderId";
    public static final String PRODUCT_ID_COLUMN = "ProductId";
    public static final String USER_ID_COLUMN = "UserId";
    public static final String PHONE_COLUMN = "Phone";
    public static final String ADDRESS_COLUMN = "Address";
    public static final String COUNT_COLUMN = "Count";

    private Long orderId;
    private Long productId;
    private Long userId;
    private String phone;
    private String address;
    private Long count;

    public OrderTbl(Long orderId, Long productId, Long userId, String phone, String address, Long count) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.phone = phone;
        this.address = address;
        this.count = count;
    }

    public OrderTbl() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderTbl{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", count=" + count +
                '}';
    }
}