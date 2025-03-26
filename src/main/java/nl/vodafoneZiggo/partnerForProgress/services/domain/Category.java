package nl.vodafoneZiggo.partnerForProgress.services.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Category {
    @Id
    private UUID id;
    private String name;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Service> services;

    protected Category() {
    }

    public Category(String name, String image, List<Service> services) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.image = image;
        this.services = new ArrayList<>(services);
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

    public List<Service> getServices() {
        return new ArrayList<>(this.services);
    }

    public void filterSearch(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return;
        }

        this.services = this.services.stream().filter(s -> s.getName().toLowerCase().contains(searchTerm.toLowerCase())).collect(Collectors.toList());
    }
}
