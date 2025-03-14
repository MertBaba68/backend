package nl.vodafoneZiggo.partnerForProgress.services.presentation;

import jdk.jfr.Name;
import nl.vodafoneZiggo.partnerForProgress.services.data.ServiceRepository;
import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ServiceControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ServiceRepository serviceRepository;
    private List<Service> services;

    @BeforeEach
    void setUp() {
        cleanUp();
        services = List.of(
                new Service("service 1", "test service", "test image"),
                new Service("service 2", "test service", "test image"),
                new Service("service 3", "test service", "test image")
        );

        this.serviceRepository.saveAll(services);
    }

    @AfterEach
    void cleanUp() {
        this.serviceRepository.deleteAll();
    }

    @Test
    @Name("Retrieving all services")
    void getServices() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/services/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(services.size()));
    }

    @Test
    @Name("Retrieving service by id")
    void getServiceById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/services/" + services.get(1).getId())).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(services.get(1).getId().toString()))
                .andExpect(jsonPath("$.name").value(services.get(1).getName()))
                .andExpect(jsonPath("$.description").value(services.get(1).getDescription()))
                .andExpect(jsonPath("$.image").value(services.get(1).getImage()));
    }
}