package by.pavelzzzzz.spring.shop.jdbc.model;

/**
 * POJO class for tblSECRole
 */

public class PasswordTbl {
    public static final String TABLE_NAME = "tblSECPassword";
    public static final String USER_ID_COLUMN = "UserId";
    public static final String PASSWORD_COLUMN = "Password";

    private Long userId;
    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Password{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                '}';
    }
}
