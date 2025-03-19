package nl.vodafoneZiggo.partnerForProgress.services.application;

import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ContactDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import nl.vodafoneZiggo.partnerForProgress.services.data.CategoriesRepository;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Category;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactServiceIntegrationTest {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        this.cleanUp();
    }

    @AfterEach
    void cleanUp(){
        this.categoriesRepository.deleteAll();
    }

    @Test
    @DisplayName("Cannot fill in contact form on non existing page")
    void contactNonExistingPage() {
        String location = "nonExistingLocation";

        NotFoundException exception = assertThrows(NotFoundException.class, () -> contactService.contact(
                new ContactDTO("41265544","CEO","test@gmail.com","0612345678",
                        "Henk Jansen",location,"I want more info" )));

        assertEquals("No location exists with name "+location, exception.getMessage());
    }

    @Test
    @DisplayName("Can fill in contact form on existing homepage")
    void contactHomepage() {
        ContactDTO contact = new ContactDTO("41265544","CEO","test@gmail.com","0612345678",
                "Henk Jansen","homepage","I want more info" );

        assertDoesNotThrow(()-> contactService.contact(contact));
    }

    @Test
    @DisplayName("Can fill in contact form on existing category")
    void contactCategoryPage() {
        Category category = new Category("Infrastructure","nope", List.of());
        this.categoriesRepository.save(category);


        ContactDTO contact = new ContactDTO("41265544","CEO","test@gmail.com","0612345678",
                "Henk Jansen",category.getName(),"I want more info" );

        assertDoesNotThrow(()-> contactService.contact(contact));
    }

    @Test
    @DisplayName("Can fill in contact form on existing service")
    void contactServicePage() {
        Category category = new Category("Infrastructure","nope", List.of(new Service("Road building","Building private roads","nope")));
        this.categoriesRepository.save(category);

        ContactDTO contact = new ContactDTO("41265544","CEO","test@gmail.com","0612345678",
                "Henk Jansen",category.getServices().get(0).getName(),"I want more info" );

        assertDoesNotThrow(()-> contactService.contact(contact));
    }
}