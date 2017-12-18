package by.pavelzzzzz.spring.shop.controllers.api;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.service.ManagementRole;
import by.pavelzzzzz.spring.shop.service.ManagementRoleForUser;
import by.pavelzzzzz.spring.shop.service.ManagementUser;
import by.pavelzzzzz.spring.shop.service.model.Role;
import by.pavelzzzzz.spring.shop.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private ManagementUser managementUser;

    @Autowired
    private ManagementRoleForUser managementRoleForUser;

    @Autowired
    private ManagementRole managementRole;

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable Long userId) throws ServiceException {
        return managementUser.findUserById(userId);
    }

    @GetMapping()
    public Set<User> getAllUsers(){
        return managementUser.getAllUsers();
    }

    @PostMapping()
    public User addNewUser(String login, String email) throws ServiceException {
        managementUser.addUser(login, email);
        return managementUser.findUserByLogin(login);
    }

    @PostMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, String login, String email, Boolean active) throws ServiceException {
        managementUser.updateUser(new User(userId, login, email, active));
        return managementUser.findUserByLogin(login);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) throws ServiceException {
        managementUser.deleteUserById(userId);
    }

    /////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/{userId}/roles")
    public Set<Role> getAllRoles(@PathVariable Long userId) throws ServiceException {
        return managementRoleForUser.getRolesByUserId(userId);
    }

    @GetMapping("/{userId}/roles/{roleId}")
    public Role getRoleByUserId(@PathVariable Long userId,
                                @PathVariable Long roleId) throws ServiceException {
        managementRoleForUser.hasRoleIdByUserId(userId, roleId);
        return managementRole.findRoleById(roleId);
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public Role addNewRoleByUserId(@PathVariable Long userId,
                                   @PathVariable Long roleId) throws ServiceException {
        managementRoleForUser.addRole(userId, roleId);
        return managementRole.findRoleById(roleId);
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public void deleteRoleForUserByRoleId(@PathVariable Long userId,
                                            @PathVariable Long roleId) throws ServiceException {
        managementRoleForUser.deleteRole(userId, roleId);
    }
}
