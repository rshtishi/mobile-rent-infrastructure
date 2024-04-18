package com.githug.rshtishi.publisher;

import com.githug.rshtishi.dto.PhoneBookedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MobileRentNotificationPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${mobile-rent.notification.exchange}")
    private String notificationExchangeName;
    @Value("${mobile-rent.notification.routing-key}")
    private String routingKey;

    public void publishNotification(PhoneBookedEvent event) {
        rabbitTemplate.convertAndSend(notificationExchangeName, routingKey, event);
    }

}
