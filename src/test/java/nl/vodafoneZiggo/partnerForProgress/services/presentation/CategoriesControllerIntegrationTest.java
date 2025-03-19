package nl.vodafoneZiggo.partnerForProgress.services.presentation;

import nl.vodafoneZiggo.partnerForProgress.services.data.CategoriesRepository;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Category;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriesControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoriesRepository categoriesRepository;
    private List<Category> categories;

    @BeforeEach
    void setUp() {
        cleanUp();

        categories = new ArrayList<>();
        categories.add(new Category("test1", "test", List.of()));
        categories.add(new Category("test2", "test", List.of(new Service("service 1", "test service", "test image","a"))));
        categories.add(new Category("test3", "test", List.of(new Service("service 2", "test service", "test image","b"), new Service("service 2", "test service", "test image","c"))));

        this.categoriesRepository.saveAll(categories);
    }

    @AfterEach
    void cleanUp() {
        this.categoriesRepository.deleteAll();
    }

    @Test
    @DisplayName("Retrieving all categories")
    void getCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(categories.size()));
    }

    @Test
    @DisplayName("Retrieving category by id")
    void getCategoryById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/" + categories.get(1).getId())).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categories.get(1).getId().toString()))
                .andExpect(jsonPath("$.name").value(categories.get(1).getName()))
                .andExpect(jsonPath("$.services.length()").value(categories.get(1).getServices().size()));
    }

    @Test
    @DisplayName("Retrieving non existing category by id")
    void getNonExistingCategoryById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/" + UUID.randomUUID())).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Retrieving category by name")
    void getCategoryByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/name/" + categories.get(1).getName())).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categories.get(1).getId().toString()))
                .andExpect(jsonPath("$.name").value(categories.get(1).getName()))
                .andExpect(jsonPath("$.services.length()").value(categories.get(1).getServices().size()));
    }

    @Test
    @DisplayName("Retrieving non existing category by name")
    void getNonExistingCategoryByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/name/kaaskroket")).andExpect(status().isNotFound());
    }
}