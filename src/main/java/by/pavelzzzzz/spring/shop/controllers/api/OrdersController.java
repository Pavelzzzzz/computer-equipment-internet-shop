package by.pavelzzzzz.spring.shop.controllers.api;

import by.pavelzzzzz.spring.shop.exception.ServiceException;
import by.pavelzzzzz.spring.shop.service.ManagementOrder;
import by.pavelzzzzz.spring.shop.service.ManagementRole;
import by.pavelzzzzz.spring.shop.service.ManagementRoleForUser;
import by.pavelzzzzz.spring.shop.service.ManagementUser;
import by.pavelzzzzz.spring.shop.service.model.Order;
import by.pavelzzzzz.spring.shop.service.model.Role;
import by.pavelzzzzz.spring.shop.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private ManagementOrder managementOrder;

    @Autowired
    private ManagementUser managementUser;

    @GetMapping("/{orderId}")
    public Order findOrderById(@PathVariable Long orderId) throws ServiceException {
        return managementOrder.findOrderById(orderId);
    }

    @GetMapping()
    public Set<Order> getAllOrders(){
        return managementOrder.getAllOrders();
    }

    @PostMapping()
    public Order addNewUser(Long productId, Long userId, String phone, String address, Long cost) throws ServiceException {
        managementOrder.addOrder(productId, userId, phone, address, cost);
        return managementOrder.getAllOrdersByUser(managementUser.findUserById(userId)).iterator().next();
    }

    @PostMapping("/{orderId}")
    public Order updateOrder(@PathVariable Long orderId, Long productId, Long userId, String phone, String address, Long count) throws ServiceException {
        managementOrder.updateOrder(new Order(
                orderId,
                productId,
                userId,
                phone,
                address,
                count));
        return managementOrder.findOrderById(orderId);
    }

    @DeleteMapping("/{orderId}")
    public void deleteUserById(@PathVariable Long orderId) throws ServiceException {
        managementOrder.deleteOrderById(orderId);
    }
}
