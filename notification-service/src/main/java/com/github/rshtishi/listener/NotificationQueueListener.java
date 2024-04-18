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

  @RabbitListener(queues = "notification.queue")
  public void handleNotificationQueue(PhoneBookedEvent notificationEvent) {
    System.out.println(notificationEvent);
    String content = "Phone booked successfully! Click below to unbook: <br/> <a href=\"https://localhost:4200/returnPhone/" + notificationEvent.phoneId() + "/" + notificationEvent.bookedBy() + "/\">Return Phone</a><br/><br/>";

    MailDto mail = new MailDto(notificationEvent.bookedBy(), "Phone Booked", content);
    mailService.sendMail(mail, true);
  }
}
