package nl.vodafoneZiggo.partnerForProgress.security.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Entity
public class User implements UserDetails {
    @Id
    private String emailAddress;
    private String password;
    private String role;

    public User() {
    }

    public User(String emailAddress, String password,  String role) {
        this.password = password;
        this.role = role;
        this.emailAddress = emailAddress;
    }

    public String getId() {
        return this.emailAddress;
    }

    @Override
    public String getUsername() {
        return this.emailAddress;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getRole() {
        return role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    public static String generatePassword() {
        Random r = new Random(System.currentTimeMillis());
        int password = 10000 + r.nextInt(20000);
        return String.valueOf(password);
    }
}
