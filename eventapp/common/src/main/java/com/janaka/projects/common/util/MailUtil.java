package com.janaka.projects.common.util;

import java.io.File;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.janaka.projects.common.constant.ApplicationConstants;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component("MailUtil")
public class MailUtil implements Serializable {

  private static final long serialVersionUID = 649816335599665740L;
  private static final Logger LOG = Logger.getLogger(MailUtil.class);

  @Autowired
  private JavaMailSender javaMailService;

  @Autowired
  private Configuration freemarkerConfiguration;

  @Value("${mail.sender}")
  private String senderEmail;


  /**
   * @param emailMessage
   * @param recieverEmailAddresses
   */
  public NotificationSentStatus sendEmail(EmailMessage emailMessage) throws MailException {
    NotificationSentStatus sentStatus = new NotificationSentStatus();
    if (!(emailMessage == null || emailMessage.getRecipient() == null
        || StringUtils.isEmpty(emailMessage.getRecipient().getRecipientAddress()))) {

      emailMessage.setRecieverName(emailMessage.getRecipient().getRecipientName());

      try {

        javaMailService.send(new MimeMessagePreparator() {

          @Override
          public void prepare(MimeMessage mimeMessage) throws Exception {

            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            String mailSubject = emailMessage.getEmailSubject();
            message.setSubject(mailSubject);
            message.setFrom(new InternetAddress(senderEmail));
            LOG.info("senderEmail: " + senderEmail);

            // message.setTo(to);
            InternetAddress toAddress =
                new InternetAddress(StringUtils.trim(emailMessage.getRecipient().getRecipientAddress()));

            message.setTo(toAddress);

            Template template = freemarkerConfiguration.getTemplate("email_template.ftl");

            Writer out = new StringWriter();
            template.process(emailMessage, out);

            String text = out.toString();

            message.setText(text, true);

            Resource resource = new ClassPathResource("email_templates/logo.jpg");
            message.addInline("logoimage", resource);
          }

        });
      } catch (Exception e) {
        e.printStackTrace();
        sentStatus.setStatus(ApplicationConstants.ERROR);
        sentStatus.setExceptionMesssage(e.getMessage());
        sentStatus.setException(e);
      }
    }
    sentStatus.setStatus(ApplicationConstants.SUCCESS);
    return sentStatus;
  }

  /**
   * @param emailMessage
   * @param recieverEmailAddresses
   * @param attachmentFileName
   * @param attachmentFilePath
   */
  public NotificationSentStatus sendEmail(EmailMessage emailMessage, String attachmentFileName,
      String attachmentFilePath) throws MailException {
    NotificationSentStatus sentStatus = new NotificationSentStatus();
    if (!(emailMessage == null || emailMessage.getRecipient() == null
        || StringUtils.isEmpty(emailMessage.getRecipient().getRecipientAddress()))) {

      emailMessage.setRecieverName(emailMessage.getRecipient().getRecipientName());

      try {

        javaMailService.send(new MimeMessagePreparator() {

          @Override
          public void prepare(MimeMessage mimeMessage) throws Exception {

            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            String mailSubject = emailMessage.getEmailSubject();
            message.setSubject(mailSubject);
            message.setFrom(new InternetAddress(senderEmail));
            LOG.info("senderEmail: " + senderEmail);

            // message.setTo(to);
            InternetAddress toAddress =
                new InternetAddress(StringUtils.trim(emailMessage.getRecipient().getRecipientAddress()));

            message.setTo(toAddress);

            Template template = freemarkerConfiguration.getTemplate("email_template.ftl");

            Writer out = new StringWriter();
            template.process(emailMessage, out);

            String text = out.toString();

            message.setText(text, true);

            // let's attach the infamous windows Sample file (this time copied to c:/)
            FileSystemResource file = new FileSystemResource(new File(attachmentFilePath));
            message.addAttachment(attachmentFileName, file);

            Resource resource = new ClassPathResource("email_templates/logo.jpg");
            message.addInline("logoimage", resource);
          }

        });
      } catch (Exception e) {
        e.printStackTrace();
        sentStatus.setStatus(ApplicationConstants.ERROR);
        sentStatus.setExceptionMesssage(e.getMessage());
        sentStatus.setException(e);
      }
    }
    sentStatus.setStatus(ApplicationConstants.SUCCESS);
    return sentStatus;
  }



}
