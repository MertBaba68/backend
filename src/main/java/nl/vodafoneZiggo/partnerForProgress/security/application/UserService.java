package nl.vodafoneZiggo.partnerForProgress.security.application;

import jakarta.transaction.Transactional;
import nl.vodafoneZiggo.partnerForProgress.security.data.UserRepository;
import nl.vodafoneZiggo.partnerForProgress.security.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String emailAddress) {
        return this.userRepository.findById(emailAddress).orElseThrow(() -> new UsernameNotFoundException(emailAddress));
    }
}