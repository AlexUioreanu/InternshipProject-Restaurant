package com.example.RestaurantOrderInfo.service;


import com.example.RestaurantOrderInfo.model.Order;
import com.sun.istack.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface OrderServiceInterface {
    void saveOrder(Order order);

    List<Order> getAllOrders();

    Optional<Order> getOrderById(Long id);

    void updateOrder(@NotNull Order order, Long id) throws IOException;

    void cancelOrder(Long id);
}
