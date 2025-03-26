package nl.vodafoneZiggo.partnerForProgress.services.presentation;

import nl.vodafoneZiggo.partnerForProgress.services.application.CategoriesService;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.CategoryDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.CategoriesSearchReq;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/name/{name}")
    public CategoryDTO getCategoryByName(@PathVariable String name, @RequestBody CategoriesSearchReq search) {
        try{
            return this.categoriesService.getCategoryByName(name, search);
        } catch (NotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}