package nl.vodafoneZiggo.partnerForProgress.services.application.dto;

import nl.vodafoneZiggo.partnerForProgress.services.domain.Category;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryDTO {
    private UUID id;
    private String name;
    private String image;
    private List<ServiceDTO> services;

    protected CategoryDTO() {
    }

    public CategoryDTO(UUID id, String name, String image, List<Service> services) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.services = services.stream().map(ServiceDTO::fromService).toList();
    }

    public static CategoryDTO fromCategory(Category category) {
        return new CategoryDTO(category.getId(),category.getName(), category.getImage(), category.getServices());
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImage() {
        return this.image;
    }

    public List<ServiceDTO> getServices() {
        return new ArrayList<>(this.services);
    }
}
