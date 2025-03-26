package nl.vodafoneZiggo.partnerForProgress.security.presentation.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nl.vodafoneZiggo.partnerForProgress.security.domain.User;
import nl.vodafoneZiggo.partnerForProgress.security.presentation.dto.Login;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final String secret;
    private final Integer expirationInMs;

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(String path, String secret, Integer expirationInMs, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(path));

        this.secret = secret;
        this.expirationInMs = expirationInMs;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        Login login = new ObjectMapper().readValue(request.getInputStream(), Login.class);

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.emailAddress(), login.password())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();

            List<String> roles = user.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            byte[] signingKey = this.secret.getBytes();

            String token = Jwts.builder()
                    .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                    .setHeaderParam("typ", "JWT")
                    .setIssuer("VodafoneZiggo")
                    .setAudience("PartnerForProgressUser")
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + this.expirationInMs))
                    .claim("role", roles)
                    .compact();

            response.addHeader("Authorization", "Bearer " + token);

            PrintWriter out = response.getWriter();

            out.print("{ \"token\": \"" + token + "\" }");

            out.flush();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
