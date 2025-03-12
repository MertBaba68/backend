package nl.vodafoneZiggo.partnerForProgress.services.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.util.UUID;

@Entity
public class Service {
    @Id
    private UUID id;
    private String name;
    private String description;

    @Lob
    private String image;

    protected Service() {
    }

    public Service(String name, String description, String image) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.image = image;
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
