package nl.vodafoneZiggo.partnerForProgress.services.application;

import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ServiceDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import nl.vodafoneZiggo.partnerForProgress.services.data.ServiceRepository;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Information;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceServiceIntegrationTest {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private ServiceRepository serviceRepository;
    private List<Service> services;

    @BeforeEach
    void setUp() {
        cleanUp();
        services = List.of(
                new Service("service 1", "test service", "test image",List.of(new Information("hello","bye",List.of("hu","ha"),"nope"))),
                new Service("service 2", "test service", "test image",List.of()),
                new Service("service 3", "test service", "test image",List.of())
        );

        this.serviceRepository.saveAll(services);
    }

    @AfterEach
    void cleanUp() {
        this.serviceRepository.deleteAll();
    }

    @Test
    @DisplayName("Retrieving all services")
    void getServices() {
        List<ServiceDTO> serviceDTOs = serviceService.getServices();
        assertEquals(services.size(), serviceDTOs.size());
    }

    @Test
    @DisplayName("Retrieving service by id")
    void getServiceById() {
        ServiceDTO serviceDTO = assertDoesNotThrow(() -> serviceService.getServiceById(services.get(0).getId()));
        Service service = services.get(0);

        assertEquals(service.getId(), serviceDTO.getId());
        assertEquals(service.getName(), serviceDTO.getName());
        assertEquals(service.getDescription(), serviceDTO.getDescription());
        assertEquals(service.getHeaderImage(), serviceDTO.getHeaderImage());
    }

    @Test
    @DisplayName("Retrieving non existed service by id")
    void getNonExistedServiceById() {
        UUID nonExistedId = UUID.randomUUID();
        NotFoundException exception = assertThrows(NotFoundException.class, () -> serviceService.getServiceById(nonExistedId));
        assertEquals("No service found with id " + nonExistedId, exception.getMessage());
    }
}