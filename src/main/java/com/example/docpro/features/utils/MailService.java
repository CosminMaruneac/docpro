package com.example.docpro.features.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

  @Value("${app.email}")
  private String EMAIL;
  private final JavaMailSender javaMailSender;

  @Autowired
  public MailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendEmail(String sendTo, String subject, String message) throws MessagingException {

    MimeMessage msg = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(msg, true);

    helper.setFrom(EMAIL);
    helper.setTo(sendTo);
    helper.setSubject(subject);
    helper.setText(message, true);

    javaMailSender.send(msg);

  }
}