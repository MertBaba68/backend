package nl.vodafoneZiggo.partnerForProgress.security.data;

import nl.vodafoneZiggo.partnerForProgress.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}