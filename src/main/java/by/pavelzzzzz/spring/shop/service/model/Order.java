package by.pavelzzzzz.spring.shop.service.model;

public class Order {

    private Long orderId;
    private Long productId;
    private Long count;
    private Long userId;
    private String phone;
    private String address;

    public Order() {
    }

    public Order(Long orderId, Long productId, Long userId, String phone, String address, Long count) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.phone = phone;
        this.address = address;
        this.count = count;
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
        return "Order{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", count=" + count +
                '}';
    }
}
