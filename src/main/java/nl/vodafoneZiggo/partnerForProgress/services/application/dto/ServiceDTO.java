package nl.vodafoneZiggo.partnerForProgress.services.application.dto;

import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServiceDTO {
    private UUID id;
    private String name;
    private String description;
    private String headerImage;
    private List<InformationDTO> about;

    protected ServiceDTO() {
    }

    public ServiceDTO(UUID id, String name, String description, String headerImage, List<InformationDTO> about) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.headerImage = headerImage;
        this.about = about;
    }

    public static ServiceDTO fromService(Service service) {
        List<InformationDTO> informationDTOS = service.getAbout().stream().map(InformationDTO::fromInformation).toList();
        return new ServiceDTO(service.getId(), service.getName(), service.getDescription(), service.getHeaderImage(), informationDTOS);
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

    public List<InformationDTO> getAbout() {
        return new ArrayList<>(this.about);
    }
}
