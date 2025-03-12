package nl.vodafoneZiggo.partnerForProgress.services.presentation;

import nl.vodafoneZiggo.partnerForProgress.services.application.CategoriesService;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.CategoryDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/")
    public List<CategoryDTO> getCategories() {
        return this.categoriesService.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable UUID id) {
        try{
            return this.categoriesService.getCategoryById(id);
        } catch (NotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}