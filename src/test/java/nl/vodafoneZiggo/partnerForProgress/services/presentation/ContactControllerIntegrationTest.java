package nl.vodafoneZiggo.partnerForProgress.services.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ContactDTO;
import nl.vodafoneZiggo.partnerForProgress.services.data.CategoriesRepository;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Category;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.cleanUp();
    }

    @AfterEach
    void cleanUp() {
        this.categoriesRepository.deleteAll();
    }

    @Test
    @DisplayName("Cannot fill in contact form on non existing page")
    void contactNonExistingPage() throws Exception {
        ContactDTO contact = new ContactDTO("41265544", "test@gmail.com", "0612345678",
                "Henk Jansen", "nonExistingLocation", "I want more info");

        mockMvc.perform(MockMvcRequestBuilders.post("/contact/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Can fill in contact form on existing homepage")
    void contactHomepage() throws Exception {
        ContactDTO contact = new ContactDTO("41265544","test@gmail.com","0612345678",
                "Henk Jansen","homepage","I want more info" );

        mockMvc.perform(MockMvcRequestBuilders.post("/contact/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Can fill in contact form on existing category")
    void contactCategoryPage() throws Exception {
        Category category = new Category("Infrastructure","nope", List.of());
        this.categoriesRepository.save(category);

        ContactDTO contact = new ContactDTO("41265544","test@gmail.com","0612345678",
                "Henk Jansen",category.getName(),"I want more info" );

        mockMvc.perform(MockMvcRequestBuilders.post("/contact/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Can fill in contact form on existing service")
    void contactServicePage() throws Exception {
        Category category = new Category("Infrastructure","nope", List.of(new Service("Road building","Building private roads","nope")));
        this.categoriesRepository.save(category);

        ContactDTO contact = new ContactDTO("41265544","test@gmail.com","0612345678",
                "Henk Jansen",category.getServices().get(0).getName(),"I want more info" );

        mockMvc.perform(MockMvcRequestBuilders.post("/contact/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isOk());
    }

    public static Stream<Arguments> provideIncorrectEmails() {
        return Stream.of(
                Arguments.of("@gmail.com"),
                Arguments.of("hoi@.com"),
                Arguments.of("hoi@."),
                Arguments.of("@."),
                Arguments.of("hallo")
        );
    }

    @ParameterizedTest
    @DisplayName("Invalid email on contact form returns 400")
    @MethodSource("provideIncorrectEmails")
    void contactInvalidEmail(String email) throws Exception {
        ContactDTO contact = new ContactDTO("41265544",email,"0612345678",
                "Henk Jansen","homepage","I want more info" );

        mockMvc.perform(MockMvcRequestBuilders.post("/contact/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isBadRequest());
    }
}