package nl.vodafoneZiggo.partnerForProgress.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest
@TestPropertySource(properties = "mail.sendMails=true")
class MailConfigTrueTest {
    @Autowired
    private MailConfig mailConfig;
    @Autowired
    private NylasMail nylas;
    @Autowired
    private PrintlnMail println;

    @Test
    @DisplayName("Should use NylasMail when mail.sendMails is true")
    void shouldUseNylasMail() {
        Mail mail = mailConfig.mailImplementation(nylas, println);
        assertInstanceOf(NylasMail.class, mail, "Expected NylasMail as the implementation");
    }
}
