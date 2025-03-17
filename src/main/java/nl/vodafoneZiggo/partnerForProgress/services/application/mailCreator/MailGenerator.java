package nl.vodafoneZiggo.partnerForProgress.services.application.mailCreator;

import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ContactDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MailGenerator {

    public static String bevestigingMail(ContactDTO contact) throws IOException {
        String htmlTemplate = new String(Files.readAllBytes(Paths.get("src/main/resources/templateEmails/bevestiging.html")));
        return String.format(htmlTemplate,
                contact.getContactPersonName(),
                contact.getLocation(),
                contact.getContactPersonName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getContext()
        );
    }

    public static String contactMail(ContactDTO contact) throws IOException {
        String htmlTemplate = new String(Files.readAllBytes(Paths.get("src/main/resources/templateEmails/contact.html")));
        return String.format(htmlTemplate,
                contact.getLocation(),
                contact.getContactPersonName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getContext()
        );
    }
}
