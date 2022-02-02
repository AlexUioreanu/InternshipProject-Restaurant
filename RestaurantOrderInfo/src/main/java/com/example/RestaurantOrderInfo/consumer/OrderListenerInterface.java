package com.example.RestaurantOrderInfo.consumer;

import com.example.RestaurantOrderInfo.config.MessagingConfig;
import com.example.RestaurantOrderInfo.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

public interface OrderListenerInterface {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    void consumeMessageFromQueue(Order order) throws IOException;
}
