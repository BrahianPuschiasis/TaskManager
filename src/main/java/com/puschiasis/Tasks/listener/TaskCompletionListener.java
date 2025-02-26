package com.puschiasis.Tasks.listener;


import com.puschiasis.Tasks.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TaskCompletionListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveTaskCompletionNotification(String taskDetails) {
        System.out.println("Received task completion notification: " + taskDetails);
    }
}
