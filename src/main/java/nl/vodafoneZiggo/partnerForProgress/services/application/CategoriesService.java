package nl.vodafoneZiggo.partnerForProgress.services.application;

import jakarta.transaction.Transactional;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.CategoriesSearchReq;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.CategoryDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import nl.vodafoneZiggo.partnerForProgress.services.data.CategoriesRepository;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<CategoryDTO> getCategories() {
        return this.categoriesRepository.findAll().stream().map(CategoryDTO::fromCategory).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(UUID id) throws NotFoundException {
        return CategoryDTO.fromCategory(this.categoriesRepository.findById(id).orElseThrow(()-> new NotFoundException("No category found with id "+id)));
    }

    public CategoryDTO getCategoryByName(String name, CategoriesSearchReq search) throws NotFoundException {
        Category category = this.categoriesRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("No category found with name " + name));

        List<nl.vodafoneZiggo.partnerForProgress.services.domain.Service> filteredServices = category.filterSearch(search.getSearchTerm());

        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getImage(),
                filteredServices
        );
    }
}
