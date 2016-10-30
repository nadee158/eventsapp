package com.janaka.projects.common.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

  @Value("${mail.host}")
  private String host;

  @Value("${mail.port}")
  private Integer port;

  @Value("${mail.sender}")
  private String senderEmail;

  @Value("${mail.authenticateUserId}")
  private String authenticateUserId;

  @Value("${mail.authenticatePwd}")
  private String authenticatePassword;

  @Value("${mail.ssLFactory}")
  private String sslFactory;

  @Bean(name = "JavaMailSender")
  public JavaMailSender javaMailService() {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    javaMailSender.setHost(host);
    javaMailSender.setPort(port);
    javaMailSender.setUsername(authenticateUserId);
    javaMailSender.setPassword(authenticatePassword);
    javaMailSender.setDefaultEncoding("UTF-8");

    Properties prop = javaMailSender.getJavaMailProperties();

    prop.put("javax.net.debug", "ssl,handshake");
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.debug", "true");

    prop.put("mail.smtp.auth", "true");
    prop.put("mail.pop3.socketFactory.class", sslFactory);
    prop.put("mail.smtp.socketFactory.class", sslFactory);

    return javaMailSender;
  }



}
