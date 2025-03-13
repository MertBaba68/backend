package nl.vodafoneZiggo.partnerForProgress.services.data;

import nl.vodafoneZiggo.partnerForProgress.services.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID>{
}
