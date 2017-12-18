package by.pavelzzzzz.spring.shop.jdbc.model;

/**
 * POJO class for tblSECRole
 */

public class UserRoleTbl {
    public static final String TABLE_NAME = "tblSECUserRole";
    public static final String USER_ID_COLUMN = "UserId";
    public static final String ROLE_ID_COLUMN = "RoleId";

    private Long userId;
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
