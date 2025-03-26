package nl.vodafoneZiggo.partnerForProgress.services.application;

import nl.vodafoneZiggo.partnerForProgress.services.application.dto.CategoriesSearchReq;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.CategoryDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CategoriesServiceIntegrationTest {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private CategoriesRepository categoriesRepository;
    private List<Category> categories;

    @BeforeEach
    void setUp() {
        cleanUp();

        categories = new ArrayList<>();
        categories.add(new Category("test1", "test", List.of()));
        categories.add(new Category("test2", "test", List.of(new Service("service 1", "test service", "test image", List.of()))));
        categories.add(new Category("test3", "test", List.of(new Service("service 2", "test service", "test image", List.of()), new Service("service 2", "test service", "test image", List.of()))));

        this.categoriesRepository.saveAll(categories);
    }

    @AfterEach
    void cleanUp() {
        this.categoriesRepository.deleteAll();
    }

    @Test
    @DisplayName("Retrieving all categories")
    void getCategories() {
        List<CategoryDTO> categoryDTOs = categoriesService.getCategories();

        assertEquals(categories.size(), categoryDTOs.size());
    }

    @Test
    @DisplayName("Retrieving category by id")
    void getCategoryById() {
        CategoryDTO categoryDTO = assertDoesNotThrow(() -> categoriesService.getCategoryById(categories.get(0).getId()));
        Category category = categories.get(0);

        assertEquals(category.getName(), categoryDTO.getName());
        assertEquals(category.getImage(), categoryDTO.getImage());
        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getServices().size(), categoryDTO.getServices().size());
    }

    @Test
    @DisplayName("Retrieving non existing Category")
    void getNonExistingCategoryById() {
        UUID id = UUID.randomUUID();
        NotFoundException e = assertThrows(NotFoundException.class, () -> categoriesService.getCategoryById(id));

        assertEquals("No category found with id " + id, e.getMessage());
    }

    @Test
    @DisplayName("Retrieving category by name")
    void getCategoryByName() {
        CategoryDTO categoryDTO = assertDoesNotThrow(() -> categoriesService.getCategoryByName(categories.get(0).getName(), new CategoriesSearchReq("")));
        Category category = categories.get(0);

        assertEquals(category.getName(), categoryDTO.getName());
        assertEquals(category.getImage(), categoryDTO.getImage());
        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getServices().size(), categoryDTO.getServices().size());
    }

    @Test
    @DisplayName("Retrieving non existing Category by name")
    void getNonExistingCategoryByName() {
        String randomName = "ewfijweoifu3289r";

        NotFoundException e = assertThrows(NotFoundException.class, () -> categoriesService.getCategoryByName(randomName, new CategoriesSearchReq("")));

        assertEquals("No category found with name " + randomName, e.getMessage());
    }
}