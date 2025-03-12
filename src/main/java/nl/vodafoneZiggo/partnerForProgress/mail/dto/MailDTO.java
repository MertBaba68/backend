package nl.vodafoneZiggo.partnerForProgress.mail.dto;

import java.util.ArrayList;
import java.util.List;

public class MailDTO {
    private String subject;
    private String body;
    private List<RecipientDTO> to;

    protected MailDTO() {
    }

    public MailDTO(String subject, String body, List<RecipientDTO> to) {
        this.subject = subject;
        this.body = body;
        this.to = new ArrayList<>(to);
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<RecipientDTO> getTo() {
        return new ArrayList<>(this.to);
    }

    public void setTo(List<RecipientDTO> to) {
        this.to = new ArrayList<>(to);
    }
}
