package nl.vodafoneZiggo.partnerForProgress.services.application.dto;

import jakarta.validation.constraints.Email;

public class ContactDTO {
    private String companyName;
    @Email(message = "Invalid email format")
    private String email;
    private String phone;
    private String contactPersonName;
    private String location;
    private String context;

    protected ContactDTO() {
    }

    public ContactDTO(String companyName, String email, String phone, String contactPersonName, String location, String context) {
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.contactPersonName = contactPersonName;
        this.location = location;
        this.context = context;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactPersonName() {
        return this.contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
