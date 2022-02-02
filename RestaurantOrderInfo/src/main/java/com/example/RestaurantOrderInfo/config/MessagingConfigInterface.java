package com.example.RestaurantOrderInfo.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

public interface MessagingConfigInterface {

    @Bean
    Queue queue();

    @Bean
    TopicExchange exchange();

    @Bean
    Binding binding(Queue queue, TopicExchange exchange);

    @Bean
    MessageConverter converter();

    @Bean
    AmqpTemplate template(ConnectionFactory connectionFactory);
}
