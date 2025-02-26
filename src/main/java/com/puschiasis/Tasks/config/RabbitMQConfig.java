package com.puschiasis.Tasks.config;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "task-exchange";
    public static final String QUEUE_NAME = "TaskQueue";
    public static final String ROUTING_KEY = "task-routing-key";

    @Value("${RABBITMQ_USERNAME}")
    private String rabbitMqUsername;

    @Value("${RABBITMQ_PASSWORD}")
    private String rabbitMqPassword;

    @Value("${RABBITMQ_HOST}")
    private String rabbitMqHost;

    @Value("${RABBITMQ_PORT}")
    private int rabbitMqPort;

    @Bean
    public org.springframework.amqp.core.Queue taskQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    public org.springframework.amqp.core.Exchange taskExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public org.springframework.amqp.core.Binding taskBinding() {
        return org.springframework.amqp.core.BindingBuilder
                .bind(taskQueue())
                .to(taskExchange())
                .with(ROUTING_KEY)
                .noargs();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory(rabbitMqHost, rabbitMqPort);
        factory.setUsername(rabbitMqUsername);
        factory.setPassword(rabbitMqPassword);
        return factory;
    }
}
