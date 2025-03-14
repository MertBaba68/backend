package nl.vodafoneZiggo.partnerForProgress.services.presentation;

import nl.vodafoneZiggo.partnerForProgress.services.application.ContactService;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ContactDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/")
    public void contact(@Validated @RequestBody ContactDTO contactDTO) throws Exception {
        try {
            this.contactService.contact(contactDTO);
        } catch (NotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
