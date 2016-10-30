package com.janaka.projects.services.business.common;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.SecurityHelper;
import com.janaka.projects.common.util.NotificationRecipient;
import com.janaka.projects.common.util.SMSUtil;
import com.janaka.projects.dtos.requests.common.SendEmailRequest;
import com.janaka.projects.services.common.NotificationService;
import com.janaka.projects.services.test.Application;
import com.janaka.projects.services.test.RepositoryConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, RepositoryConfiguration.class})
public class TestNotificationService {


  @Autowired
  private SMSUtil smsUtil;

  @Autowired
  private NotificationService notificationService;

  @Test
  public void testSendEmail() {
    String title = "தலைப்பு";
    String emailSubject = "மூன்றாவது முறையாக மின்னஞ்சல்கள் சோதனை";
    String recieverName = "அருண்யா";
    String bodyText =
        "வலயம் வெறுமனே அச்சிடுதல் மற்றும் அச்சு தொழில் போலி உரை உள்ளது. வலயம் 1500, அறியப்படாத ஒரு அச்சுப்பொறி வகை ஒரு தாழ்வான எடுத்து ஒரு வகை மாதிரி புத்தகத்தை செய்ய துருவல் போது முதல் தொழில் தரமான போலி எப்போதும் உரை உள்ளது. அது அடிப்படையில் மாறாமல் மீதமுள்ள, பல நூற்றாண்டுகளாக, ஆனால் மின்னணு அச்சு மீது பாய்ச்சல் இல்லை ஐந்து மட்டுமே பிழைத்து. அது வலயம் பதிப்புகள் உட்பட Aldus PageMaker போன்ற டெஸ்க்டாப் வெளியிடுதல் மென்பொருட்கள் வலயம் பத்திகளை கொண்ட Letraset தாள்கள் வெளியீடு 1960 ஆம் பிரபலமானது, மேலும் சமீபத்தில் இருந்தது.";
    String language = ApplicationConstants.TAMIL;

    NotificationRecipient recipients = new NotificationRecipient(recieverName, "nadee158@gmail.com", 0);
    List<NotificationRecipient> recipientss = new ArrayList<NotificationRecipient>();
    recipientss.add(recipients);

    SendEmailRequest request = new SendEmailRequest();
    request.setBodyText(bodyText);
    request.setEmailSubject(emailSubject);
    request.setEmailType(1);
    request.setLanguage(language);
    request.setRecepients(recipientss);
    request.setTitle(title);
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();

    notificationService.sendEmail(request, auditContext, securityContext);
  }

  // @Test
  public void testReSendEmail() {
    notificationService.reSendEmails();
  }


  // @Test
  public void testSendSMS() {
    // notificationService.sendSMS(request, auditContext, securityContext);

    String outSms = "Rubber Test SMS Message";
    String recepient = "0712186182";
    try {
      String status = smsUtil.sendSMS(outSms, recepient);
      System.out.println("status :" + status);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // @Test
  public void testReSendSMS() {
    notificationService.reSendSmses();

    String outSms = "Rubber Test SMS Message";
    String recepient = "0712186182";
    try {
      String status = smsUtil.sendSMS(outSms, recepient);
      System.out.println("status :" + status);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



}
