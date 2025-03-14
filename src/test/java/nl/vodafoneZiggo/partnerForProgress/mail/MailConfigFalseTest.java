package nl.vodafoneZiggo.partnerForProgress.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest
@TestPropertySource(properties = "mail.sendMails=false")
class MailConfigFalseTest {
    @Autowired
    private MailConfig mailConfig;
    @Autowired
    private NylasMail nylas;
    @Autowired
    private PrintlnMail println;

    @Test
    @DisplayName("Should use PrintlnMail when mail.sendMails is false")
    void shouldUseNylasMail() {
        Mail mail = mailConfig.mailImplementation(nylas, println);

        assertInstanceOf(PrintlnMail.class, mail, "Expected PrintlnMail as the implementation");
    }
}
