package nl.vodafoneZiggo.partnerForProgress.services.application;

import nl.vodafoneZiggo.partnerForProgress.mail.Mail;
import nl.vodafoneZiggo.partnerForProgress.mail.exception.InvalidEmailException;
import nl.vodafoneZiggo.partnerForProgress.mail.exception.MailException;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ContactDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import nl.vodafoneZiggo.partnerForProgress.services.application.mailCreator.MailGenerator;
import nl.vodafoneZiggo.partnerForProgress.services.data.CategoriesRepository;
import nl.vodafoneZiggo.partnerForProgress.services.data.ServiceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private final Mail mail;
    private final ServiceRepository serviceRepository;
    private final CategoriesRepository categoriesRepository;

    @Value("${mail.partner-for-progress-email}")
    private String partnerForProgressEmail;

    public ContactService(Mail mail, ServiceRepository serviceRepository, CategoriesRepository categoriesRepository) {
        this.mail = mail;
        this.serviceRepository = serviceRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public void contact(ContactDTO contact) throws Exception {
        if (!contact.getLocation().equals("homepage")) {
            if (!this.categoriesRepository.existsByName(contact.getLocation())){
                if (!this.serviceRepository.existsByName(contact.getLocation())){
                    throw new NotFoundException("No location exists with name "+contact.getLocation());
                }
            }
        }

        try {
            this.mail.sendEmail(partnerForProgressEmail, "Nieuwe aanvraag op " + contact.getLocation(),
                    MailGenerator.contactMail(contact));
            this.mail.sendEmail(contact.getEmail(), "Bevestiging Partner for Progress",
                    MailGenerator.confirmationMail(contact));
        } catch (MailException e) {
            throw new Exception(e.getMessage());
        } catch (InvalidEmailException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
