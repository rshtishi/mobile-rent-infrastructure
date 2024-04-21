package com.github.rshtishi.listener;

import com.github.rshtishi.dto.PhoneBookedEvent;
import com.github.rshtishi.mail.MailDto;
import com.github.rshtishi.mail.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationQueueListener {

    private final MailService mailService;

    @Autowired
    public NotificationQueueListener(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = "mail.queue")
    public void handleNotificationQueue(PhoneBookedEvent notificationEvent) {
        String content = new StringBuilder()
                .append("Phone booked successfully! Click below to unbook: <br/>")
                .append("<a href=\"http://localhost:4200/returnPhone/")
                .append(notificationEvent.getPhoneId())
                .append("/")
                .append(notificationEvent.getBookedBy())
                .append("\">Return Phone</a><br/><br/>")
                .toString();

        MailDto mail = new MailDto(notificationEvent.getBookedBy(), "Phone Booked", content);
        mailService.sendMail(mail, true);
        System.out.println("Event processed successfully!");
    }
}
