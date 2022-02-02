package com.example.RestaurantOrderInfo.service;

import com.example.RestaurantOrderInfo.config.MessagingConfig;
import com.example.RestaurantOrderInfo.exceptions.NoOrderInDB;
import com.example.RestaurantOrderInfo.model.Order;
import com.example.RestaurantOrderInfo.model.OrderRepository;
import com.sun.istack.NotNull;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService implements OrderServiceInterface {


    @Autowired
    private OrderRepository repo;

    @Autowired
    private RabbitTemplate template;

    @PostConstruct
    void prePopulate() {
        ArrayList<Order> orders = new ArrayList<>();
        Order order1 = Order.builder().name("Vasile").qty(100).price(200).status("Succes").message("Order placed").build();
        Order order2 = Order.builder().name("Vasile").qty(100).price(200).status("Succes").message("Order placed").build();
        Order order3 = Order.builder().name("Vasile").qty(100).price(200).status("Succes").message("Order placed").build();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        repo.saveAll(orders);
    }

    @Override
    public void saveOrder(Order order) {
        order = Order.builder().name(order.getName()).qty(order.getQty()).price(order.getPrice()).status(order.getStatus()).message("Order placed succesfully in ").build();

        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, order);
        repo.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void updateOrder(@NotNull Order order, Long id) throws IOException {
        Optional<Order> order1 = repo.findById(id);
        if (order1.isPresent()) {
            if (order.getName() != null) {
                order1.get().setName(order.getName());
            }
            if (order.getQty() != 0) {
                order1.get().setQty(order.getQty());
            }
            if (order.getPrice() != 0) {
                order1.get().setPrice(order.getPrice());
            }
            if (order.getStatus() != null) {
                order1.get().setStatus(order.getStatus());
            }
            if (order.getMessage() != null) {
                order1.get().setMessage(order.getMessage());
            }
            repo.save(order1.get());
        } else {
            throw new NoOrderInDB("The order you want to update doesn't exists!");
        }
    }

    @Override
    public void cancelOrder(Long id) {
        repo.deleteById(id);
    }
}
