package com.example.RestaurantOrderInfo.api;

import com.example.RestaurantOrderInfo.model.Order;
import com.example.RestaurantOrderInfo.model.OrderList;
import com.example.RestaurantOrderInfo.service.OrderServiceInterface;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping("/order")
public class OrderApi {

    @Autowired
    private OrderServiceInterface orderService;

    @PostMapping
    public void saveOrder(@RequestBody @NotNull Order order) {
        orderService.saveOrder(order);
    }

    @GetMapping(value = "/allOrders")
    @RolesAllowed("admin")
    public OrderList getAllOrders() {
        return new OrderList(orderService.getAllOrders());
    }

    @GetMapping("/get/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
    }

    @PatchMapping("/update/{id}")
    public void updateOrder(@RequestBody Order order, @PathVariable Long id) throws IOException {
        orderService.updateOrder(order, id);

    }
}
