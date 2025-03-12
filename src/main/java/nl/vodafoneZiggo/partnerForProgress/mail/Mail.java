package nl.vodafoneZiggo.partnerForProgress.mail;

import nl.vodafoneZiggo.partnerForProgress.mail.exception.MailException;
import org.springframework.stereotype.Component;

@Component
public interface Mail {
    void sendEmail(String to, String subject, String body) throws MailException;
}
