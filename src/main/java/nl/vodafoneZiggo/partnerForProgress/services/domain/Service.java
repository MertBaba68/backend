package nl.vodafoneZiggo.partnerForProgress.services.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.util.UUID;

@Entity
public class Service {
    @Id
    private UUID id;
    private String name;
    private String smallDescription;
    private String description;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String headerImage;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String secondaryImage;

    protected Service() {
    }

    public Service(String name, String description, String headerImage, String secondaryImage) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.headerImage = headerImage;
        this.secondaryImage = secondaryImage;
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
