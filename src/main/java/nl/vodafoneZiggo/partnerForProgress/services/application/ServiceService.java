package nl.vodafoneZiggo.partnerForProgress.services.application;

import jakarta.transaction.Transactional;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ServiceDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import nl.vodafoneZiggo.partnerForProgress.services.data.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDTO> getServices() {
        return this.serviceRepository.findAll().stream().map(ServiceDTO::fromService).collect(Collectors.toList());
    }

    public ServiceDTO getServiceById(UUID id) throws NotFoundException {
        return ServiceDTO.fromService(this.serviceRepository.findById(id).orElseThrow(()-> new NotFoundException("No service found with id "+id)));
    }
}
