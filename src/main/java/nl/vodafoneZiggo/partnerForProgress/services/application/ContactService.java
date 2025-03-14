package nl.vodafoneZiggo.partnerForProgress.services.application;

import nl.vodafoneZiggo.partnerForProgress.mail.Mail;
import nl.vodafoneZiggo.partnerForProgress.mail.exception.MailException;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ContactDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
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
            //TODO: Mannan will replace this with HTML formatted email content
            this.mail.sendEmail(partnerForProgressEmail, "Ingevuld contactformulier " + contact.getLocation(),
                    "Contactformulier ingevuld voor " + contact.getLocation() + "\nEmail van invuller " +
                            contact.getEmail() + "\nTelefoonnr van invuller " + contact.getPhone() + "\nNaam van invuller " +
                            contact.getContactPersonName() + "\nIngevulde context:\n" + contact.getContext());
        } catch (MailException e) {
            throw new Exception(e.getMessage());
        }
    }
}
