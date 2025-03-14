package nl.vodafoneZiggo.partnerForProgress.services.application;

import jdk.jfr.Name;
import nl.vodafoneZiggo.partnerForProgress.services.application.dto.ServiceDTO;
import nl.vodafoneZiggo.partnerForProgress.services.application.exception.NotFoundException;
import nl.vodafoneZiggo.partnerForProgress.services.data.ServiceRepository;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServiceServiceTest {
    private ServiceService serviceService;
    private ServiceRepository serviceRepository;
    private List<Service> services;

    @BeforeEach
    void setUp() {
        serviceRepository = mock(ServiceRepository.class);
        serviceService = new ServiceService(serviceRepository);
        services = List.of(
                new Service("service 1", "test service", "test image"),
                new Service("service 2", "test service", "test image"),
                new Service("service 3", "test service", "test image")
        );
    }

    @Test
    @Name("Retrieving all services")
    void getServices() {
        when(serviceRepository.findAll()).thenReturn(services);

        List<ServiceDTO> serviceDTOs = serviceService.getServices();

        assertEquals(services.size(), serviceDTOs.size());
        for (int serviceI = 0; serviceI < services.size(); serviceI++) {
            ServiceDTO serviceDTO = serviceDTOs.get(serviceI);
            Service service = services.get(serviceI);

            assertEquals(service.getName(), serviceDTO.getName());
            assertEquals(service.getDescription(), serviceDTO.getDescription());
            assertEquals(service.getImage(), serviceDTO.getImage());
        }
    }

    @Test
    @Name("Retrieving a service by id")
    void getServiceById() {
        when(serviceRepository.findById(any())).thenReturn(Optional.of(services.get(0)));

        ServiceDTO serviceDTO = assertDoesNotThrow(() -> serviceService.getServiceById(services.get(0).getId()));
        Service service = services.get(0);
        assertEquals(service.getName(), serviceDTO.getName());
        assertEquals(service.getDescription(), serviceDTO.getDescription());
        assertEquals(service.getImage(), serviceDTO.getImage());
        assertEquals(service.getId(), serviceDTO.getId());
    }

    @Test
    @Name("Retrieving a service by id that does not exist")
    void getServiceByIdNotFound() {
        when(serviceRepository.findById(any())).thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> serviceService.getServiceById(services.get(0).getId()));

        assertEquals("No service found with id "+services.get(0).getId(), notFoundException.getMessage());
    }
}