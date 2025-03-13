package nl.vodafoneZiggo.partnerForProgress.mail;

import nl.vodafoneZiggo.partnerForProgress.mail.exception.MailException;
import org.springframework.stereotype.Component;

@Component
public class PrintlnMail implements Mail {
    public PrintlnMail(){
    }

    @Override
    public void sendEmail(String to, String subject, String body) throws MailException {
        System.out.println("PrintlnMail component received request to email to "+to+"\nwith subject "+subject+"\nand body "+body);
    }
}
