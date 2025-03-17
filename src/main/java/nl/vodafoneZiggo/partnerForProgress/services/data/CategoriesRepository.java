package nl.vodafoneZiggo.partnerForProgress.services.data;

import nl.vodafoneZiggo.partnerForProgress.services.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoriesRepository extends JpaRepository<Category, UUID> {
    boolean existsByName(String name);
    Optional<Category> findByName(String name);
}
