package by.pavelzzzzz.spring.shop.controllers.api;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.service.ManagementRole;
import by.pavelzzzzz.spring.shop.service.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    @Autowired
    private ManagementRole managementRole;

    @GetMapping("/{roleId}")
    public Role findRoleById(@PathVariable Long roleId) throws ServiceException {
        return managementRole.findRoleById(roleId);
    }

    @PostMapping("/{roleId}")
    public Role updateRoleById(@PathVariable Long roleId, String role) throws ServiceException {
        managementRole.updateRole(roleId, role);
        return managementRole.findRoleById(roleId);
    }

    @GetMapping()
    public Set<Role> getAllRoles(){
        return managementRole.getAllRoles();
    }

    @PostMapping()
    public Role addNewRole(String roleName) throws ServiceException {
        managementRole.addRole(roleName);
        return managementRole.findRoleByName(roleName);
    }

    @DeleteMapping("/{roleId}")
    public void deleteRoleById(@PathVariable Long roleId) throws ServiceException {
        managementRole.deleteRole(roleId);
    }
}
