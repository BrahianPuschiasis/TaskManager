package com.puschiasis.Tasks.notification;

import com.puschiasis.Tasks.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskNotificationServiceImpl implements TaskNotificationService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TaskNotificationServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendTaskCompletionNotification(String taskDetails) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, taskDetails);
    }
}