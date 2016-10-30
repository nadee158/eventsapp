package com.janaka.projects.services.business.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.util.EmailMessage;
import com.janaka.projects.common.util.MailUtil;
import com.janaka.projects.common.util.NotificationRecipient;
import com.janaka.projects.services.test.Application;
import com.janaka.projects.services.test.RepositoryConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, RepositoryConfiguration.class})
public class TestEmailService {


  @Autowired
  private MailUtil mailUtil;


  @Test
  public void testSendEmail() {

    String title = "தலைப்பு";
    String emailSubject = "மூன்றாவது முறையாக மின்னஞ்சல்கள் சோதனை";
    String recieverName = "அருண்யா";
    String bodyText =
        "வலயம் வெறுமனே அச்சிடுதல் மற்றும் அச்சு தொழில் போலி உரை உள்ளது. வலயம் 1500, அறியப்படாத ஒரு அச்சுப்பொறி வகை ஒரு தாழ்வான எடுத்து ஒரு வகை மாதிரி புத்தகத்தை செய்ய துருவல் போது முதல் தொழில் தரமான போலி எப்போதும் உரை உள்ளது. அது அடிப்படையில் மாறாமல் மீதமுள்ள, பல நூற்றாண்டுகளாக, ஆனால் மின்னணு அச்சு மீது பாய்ச்சல் இல்லை ஐந்து மட்டுமே பிழைத்து. அது வலயம் பதிப்புகள் உட்பட Aldus PageMaker போன்ற டெஸ்க்டாப் வெளியிடுதல் மென்பொருட்கள் வலயம் பத்திகளை கொண்ட Letraset தாள்கள் வெளியீடு 1960 ஆம் பிரபலமானது, மேலும் சமீபத்தில் இருந்தது.";
    String language = ApplicationConstants.TAMIL;

    NotificationRecipient recipients = new NotificationRecipient(recieverName, "nadee158@gmail.com", 0);

    EmailMessage emailMessage = new EmailMessage(title, emailSubject, recipients, bodyText, language);

    try {
      mailUtil.sendEmail(emailMessage);
    } catch (MailPreparationException mpe) {
      // in case of failure when preparing the message
      System.out.println("MailPreparationException");
      mpe.printStackTrace();
    } catch (MailParseException mpae) {
      // in case of failure when parsing the message
      System.out.println("MailParseException");
      mpae.printStackTrace();
    } catch (MailAuthenticationException mae) {
      // in case of authentication failure
      System.out.println("MailAuthenticationException");
      mae.printStackTrace();
    } catch (MailSendException mse) {
      // in case of failure when sending the message
      System.out.println("MailSendException");
      mse.printStackTrace();
    } catch (Exception e) {
      System.out.println("Exception");
      e.printStackTrace();
    }
  }

}
