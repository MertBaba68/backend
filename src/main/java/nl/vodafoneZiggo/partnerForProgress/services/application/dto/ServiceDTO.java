package nl.vodafoneZiggo.partnerForProgress.services.application.dto;

import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;

import java.util.UUID;

public class ServiceDTO {
    private UUID id;
    private String name;
    private String description;
    private String headerImage;
    private String secondaryImage;

    protected ServiceDTO() {
    }

    public ServiceDTO(UUID id, String name, String description, String headerImage, String secondaryImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.headerImage = headerImage;
        this.secondaryImage = secondaryImage;
    }

    public static ServiceDTO fromService(Service service) {
        return new ServiceDTO(service.getId(), service.getName(), service.getDescription(), service.getHeaderImage(), service.getSecondaryImage());
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

    public String getHeaderImage() {
        return this.headerImage;
    }

    public String getSecondaryImage() {
        return this.secondaryImage;
    }
}
