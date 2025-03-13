package nl.vodafoneZiggo.partnerForProgress.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MailConfig {
    @Value("${mail.sendMails}")
    private boolean sendMails;

    @Bean
    @Primary
    public Mail mailImplementation(NylasMail nylas, PrintlnMail println) {
        return sendMails ? nylas : println;
    }
}
