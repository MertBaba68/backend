package nl.vodafoneZiggo.partnerForProgress.mail.dto;

public class RecipientDTO {
    private String email;

    protected RecipientDTO() {
    }

    public RecipientDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
