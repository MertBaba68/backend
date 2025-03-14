package nl.vodafoneZiggo.partnerForProgress.services.presentation;

import nl.vodafoneZiggo.partnerForProgress.services.application.ServiceService;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ServiceDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/")
    public List<ServiceDTO> getServices() {
        return this.serviceService.getServices();
    }
    @GetMapping("/{id}")
    public ServiceDTO getServiceById(@PathVariable UUID id) {
        try {
            return this.serviceService.getServiceById(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
