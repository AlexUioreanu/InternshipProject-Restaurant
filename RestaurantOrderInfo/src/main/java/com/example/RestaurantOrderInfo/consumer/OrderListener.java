package com.example.RestaurantOrderInfo.consumer;

import com.example.RestaurantOrderInfo.config.MessagingConfig;
import com.example.RestaurantOrderInfo.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener implements OrderListenerInterface {

    @Override
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Order order) {
        messageDisplay(order);
    }

    private void messageDisplay(Order order) {
        System.out.println("Message recieved from queue : " + order);
    }
}
