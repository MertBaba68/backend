package nl.vodafoneZiggo.partnerForProgress.services.domain;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Information {
    @Id
    private UUID id;
    private String title;
    private String description;

    @ElementCollection
    @CollectionTable(name = "Information_list", joinColumns = @JoinColumn(name = "list_item_id"))
    @Column(name = "list_item")
    private List<String> list;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    public Information(String title, String description, List<String> list, String image) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.list = list;
        this.image = image;
    }

    public Information(String title, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
    }

    protected Information() {
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
