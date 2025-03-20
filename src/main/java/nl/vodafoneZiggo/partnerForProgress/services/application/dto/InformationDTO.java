package nl.vodafoneZiggo.partnerForProgress.services.application.dto;

import nl.vodafoneZiggo.partnerForProgress.services.domain.Information;

import java.util.List;
import java.util.UUID;

public class InformationDTO {
    private UUID id;
    private String title;
    private String description;
    private List<String> list;
    private String image;

    public InformationDTO(UUID id, String title, String description, List<String> list, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.list = list;
        this.image = image;
    }

    protected InformationDTO() {
    }

    public static InformationDTO fromInformation(Information information) {
        return new InformationDTO(information.getId(), information.getTitle(), information.getDescription(), information.getList(), information.getImage());
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getList() {
        return this.list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
