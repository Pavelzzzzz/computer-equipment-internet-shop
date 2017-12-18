package by.pavelzzzzz.spring.shop.jdbc.model;

/**
 * POJO class for tblSECRole
 */

public class RoleTbl {
    public static final String TABLE_NAME = "tblSECRole";
    public static final String ROLE_ID_COLUMN = "RoleId";
    public static final String ROLE_COLUMN = "Role";

    private Long roleId;
    private String role;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }
}
