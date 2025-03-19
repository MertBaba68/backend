package nl.vodafoneZiggo.partnerForProgress.services.application;

import nl.vodafoneZiggo.partnerForProgress.mail.Mail;
import nl.vodafoneZiggo.partnerForProgress.mail.exception.InvalidEmailException;
import nl.vodafoneZiggo.partnerForProgress.mail.exception.MailException;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ContactDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import nl.vodafoneZiggo.partnerForProgress.services.data.CategoriesRepository;
import nl.vodafoneZiggo.partnerForProgress.services.data.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContactServiceTest {
    private CategoriesRepository categoriesRepository;
    private ServiceRepository serviceRepository;
    private ContactService contactService;
    private Mail mail;
    private String email;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        categoriesRepository = mock(CategoriesRepository.class);
        serviceRepository = mock(ServiceRepository.class);
        mail = mock(Mail.class);

        email = "example@gmail.com";

        contactService = new ContactService(mail, serviceRepository, categoriesRepository);

        Field field = ContactService.class.getDeclaredField("partnerForProgressEmail");
        field.setAccessible(true);
        field.set(contactService, email);
    }

    @Test
    @DisplayName("Cannot fill in contact form on non existing page")
    void contactNonExistingPage() throws MailException, InvalidEmailException {
        when(categoriesRepository.existsByName(anyString())).thenReturn(false);
        when(serviceRepository.existsByName(anyString())).thenReturn(false);

        String location = "nonExistingLocation";

        NotFoundException exception = assertThrows(NotFoundException.class, () -> contactService.contact(
                new ContactDTO("41265544","CEO","test@gmail.com","0612345678",
                        "Henk Jansen",location,"I want more info" )));

        assertEquals("No location exists with name "+location, exception.getMessage());

        verify(mail, times(0)).sendEmail(anyString(),anyString(),anyString());
    }

    @Test
    @DisplayName("Can fill in contact form on existing homepage")
    void contactHomepage() throws MailException, InvalidEmailException {
        when(categoriesRepository.existsByName(anyString())).thenReturn(false);
        when(serviceRepository.existsByName(anyString())).thenReturn(false);

        ContactDTO contact = new ContactDTO("41265544","CEO","test@gmail.com","0612345678",
                "Henk Jansen","homepage","I want more info" );

        assertDoesNotThrow(()-> contactService.contact(contact));

        verify(mail, times(1)).sendEmail(eq(email),anyString(),anyString());
        verify(mail, times(1)).sendEmail(eq(contact.getEmail()),anyString(),anyString());
    }

    @Test
    @DisplayName("Can fill in contact form on existing category")
    void contactCategoryPage() throws MailException, InvalidEmailException {
        when(categoriesRepository.existsByName(anyString())).thenReturn(true);
        when(serviceRepository.existsByName(anyString())).thenReturn(false);

        ContactDTO contact = new ContactDTO("41265544","CEO","test@gmail.com","0612345678",
                "Henk Jansen","Infrastructure","I want more info" );

        assertDoesNotThrow(()-> contactService.contact(contact));

        verify(mail, times(1)).sendEmail(eq(email),anyString(),anyString());
        verify(mail, times(1)).sendEmail(eq(contact.getEmail()),anyString(),anyString());
    }

    @Test
    @DisplayName("Can fill in contact form on existing service")
    void contactServicePage() throws MailException, InvalidEmailException {
        when(categoriesRepository.existsByName(anyString())).thenReturn(false);
        when(serviceRepository.existsByName(anyString())).thenReturn(true);

        ContactDTO contact = new ContactDTO("41265544","CEO","test@gmail.com","0612345678",
                "Henk Jansen","Road building","I want more info" );

        assertDoesNotThrow(()-> contactService.contact(contact));

        verify(mail, times(1)).sendEmail(eq(email),anyString(),anyString());
        verify(mail, times(1)).sendEmail(eq(contact.getEmail()),anyString(),anyString());
    }
}