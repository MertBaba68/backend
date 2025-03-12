package nl.vodafoneZiggo.partnerForProgress.services.application.dto;

import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;

import java.util.UUID;

public class ServiceDTO {
    private UUID id;
    private String name;
    private String description;
    private String image;

    protected ServiceDTO() {
    }

    public ServiceDTO(UUID id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public static ServiceDTO fromService(Service service) {
        return new ServiceDTO(service.getId(), service.getName(), service.getDescription(), service.getImage());
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }
}
