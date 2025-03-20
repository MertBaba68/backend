package nl.vodafoneZiggo.partnerForProgress.services.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Information> about;

    protected Service() {
    }

    public Service(String name, String description, String headerImage, List<Information> about) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.headerImage = headerImage;
        this.about = about;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSmallDescription() {
        return this.smallDescription;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHeaderImage() {
        return this.headerImage;
    }

    public List<Information> getAbout() {
        return new ArrayList<>(this.about);
    }
}
