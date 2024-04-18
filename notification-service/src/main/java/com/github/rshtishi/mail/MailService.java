package com.github.rshtishi.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

  private final JavaMailSender mailSender;

  @Autowired
  public MailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }


  private MimeMessage prepareMessage(MailDto mail, boolean htmlContentEnabled){
    MimeMessage message = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true); // Set true for multipart messages
      helper.setTo(mail.to());
      helper.setSubject(mail.subject());
      helper.setText(mail.content(), htmlContentEnabled);
    } catch (MessagingException e) {
      // Handle exception appropriately (e.g., log error)
    }
    return message;
  }

  public void sendMail(MailDto mail, boolean htmlEnabled) {
    MimeMessage message = prepareMessage(mail, htmlEnabled);
    mailSender.send(message);
  }
}
