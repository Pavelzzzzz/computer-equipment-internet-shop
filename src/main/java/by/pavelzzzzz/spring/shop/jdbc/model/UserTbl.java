package by.pavelzzzzz.spring.shop.jdbc.model;

/**
 * POJO class for tblSECUser
 */

public class UserTbl {
    public static final String TABLE_NAME = "tblSECUser";
    public static final String USER_ID_COLUMN = "UserId";
    public static final String LOGIN_COLUMN = "Login";
    public static final String EMAIL_COLUMN = "Email";
    public static final String IS_ACTIVE_COLUMN = "IsActive";

    private Long userId;
    private String login;
    private String email;
    private boolean isActive = false;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UserTbl{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
