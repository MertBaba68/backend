package nl.vodafoneZiggo.partnerForProgress.services.application;

import nl.vodafoneZiggo.partnerForProgress.services.application.dto.CategoryDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ServiceDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import nl.vodafoneZiggo.partnerForProgress.services.data.CategoriesRepository;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Category;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoriesServiceTest {
    private CategoriesService categoriesService;
    private CategoriesRepository categoriesRepository;
    private List<Category> categories;

    @BeforeEach
    void setUp() {
        categoriesRepository = mock(CategoriesRepository.class);
        categoriesService = new CategoriesService(categoriesRepository);

        categories = new ArrayList<>();
        categories.add(new Category("test1", "test", List.of()));
        categories.add(new Category("test2", "test", List.of(new Service("service 1", "test service", "test image"))));
        categories.add(new Category("test3", "test", List.of(new Service("service 2", "test service", "test image"), new Service("service 2", "test service", "test image"))));
    }

    @Test
    @DisplayName("Retrieving all categories")
    void getCategories() {
        when(categoriesRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOs = categoriesService.getCategories();

        assertEquals(categories.size(), categoryDTOs.size());
        for (int categoryI = 0; categoryI < categories.size(); categoryI++) {
            CategoryDTO categoryDTO = categoryDTOs.get(categoryI);
            Category category = categories.get(categoryI);
            assertEquals(category.getName(), categoryDTO.getName());
            assertEquals(category.getImage(), categoryDTO.getImage());
            assertEquals(category.getId(), categoryDTO.getId());
            assertEquals(category.getServices().size(), categoryDTO.getServices().size());

            for (int serviceI = 0; serviceI < category.getServices().size(); serviceI++) {
                ServiceDTO serviceDTO = categoryDTO.getServices().get(serviceI);
                Service service = category.getServices().get(serviceI);

                assertEquals(service.getName(), serviceDTO.getName());
                assertEquals(service.getDescription(), serviceDTO.getDescription());
                assertEquals(service.getImage(), serviceDTO.getImage());
                assertEquals(service.getId(), serviceDTO.getId());
            }
        }
    }

    @Test
    @DisplayName("Retrieving category by id")
    void getCategoryById() {
        when(categoriesRepository.findById(any())).thenReturn(Optional.of(categories.get(0)));

        CategoryDTO categoryDTO = assertDoesNotThrow(() -> categoriesService.getCategoryById(categories.get(0).getId()));
        Category category = categories.get(0);

        assertEquals(category.getName(), categoryDTO.getName());
        assertEquals(category.getImage(), categoryDTO.getImage());
        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getServices().size(), categoryDTO.getServices().size());

        for (int serviceI = 0; serviceI < category.getServices().size(); serviceI++) {
            ServiceDTO serviceDTO = categoryDTO.getServices().get(serviceI);
            Service service = category.getServices().get(serviceI);

            assertEquals(service.getName(), serviceDTO.getName());
            assertEquals(service.getDescription(), serviceDTO.getDescription());
            assertEquals(service.getImage(), serviceDTO.getImage());
            assertEquals(service.getId(), serviceDTO.getId());
        }
    }

    @Test
    @DisplayName("Retrieving non existing Category")
    void getNonExistingCategoryById() {
        when(categoriesRepository.findById(any())).thenReturn(Optional.empty());

        NotFoundException e = assertThrows(NotFoundException.class, () -> categoriesService.getCategoryById(categories.get(0).getId()));

        assertEquals("No category found with id "+categories.get(0).getId(), e.getMessage());
    }
}