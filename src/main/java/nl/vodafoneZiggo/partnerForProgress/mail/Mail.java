package nl.vodafoneZiggo.partnerForProgress.mail;

import nl.vodafoneZiggo.partnerForProgress.mail.dto.MailDTO;
import nl.vodafoneZiggo.partnerForProgress.mail.dto.RecipientDTO;
import nl.vodafoneZiggo.partnerForProgress.mail.exception.MailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Mail {
    @Value("${mail.grant-id}")
    private String grantID;
    @Value("${mail.authorization}")
    private String authorization;

    public Mail() {
    }

    public void sendEmail(String to, String subject, String body) throws MailException {
        System.out.println("grantID:"+grantID);
        System.out.println("auth:"+authorization);
        RestTemplate restTemplate = new RestTemplate();
        MailDTO mailDTO = new MailDTO(subject, body, List.of(new RecipientDTO(to)));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("Authorization", this.authorization);

        HttpEntity<MailDTO> requestEntity = new HttpEntity<>(mailDTO, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                "https://api.us.nylas.com/v3/grants/" + this.grantID + "/messages/send",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        if (response.getStatusCode().value() != 200) {
            throw new MailException("Failed to send mail to " + to + ", statusCode=" + response.getStatusCode());
        }
    }
}
