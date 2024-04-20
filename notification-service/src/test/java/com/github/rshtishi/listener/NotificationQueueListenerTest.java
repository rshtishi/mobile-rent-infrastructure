package com.github.rshtishi.listener;


import com.github.rshtishi.config.RabbitMQTestContainer;
import com.github.rshtishi.dto.PhoneBookedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class NotificationQueueListenerTest implements RabbitMQTestContainer {


    @Autowired
    RabbitTemplate rabbitTemplate;
    @Value("${mobile-rent.notification.exchange}")
    private String notificationExchange;
    @Value("${mobile-rent.notification.routing-key}")
    private String routingKey;


    @Test
    public void testHandleNotificationQueue(CapturedOutput capturedOutput) {
        PhoneBookedEvent phoneBookedEvent = new PhoneBookedEvent(1L, "Samsung","test@mail.com");
        rabbitTemplate.convertAndSend(notificationExchange, routingKey, phoneBookedEvent);
        await().atMost(Duration.ofSeconds(15))
                .until(()->{
                    return capturedOutput.getAll().contains("Event processed successfully!");
                });
    }

}