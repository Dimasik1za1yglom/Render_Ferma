package example.demo.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authManager;
    private final UserDetailsService userDetailsServiceImpl;

    public void setUpSecurity(String login, String pass) {
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(login, pass);
        try {
            authManager.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
            Authentication auth = authManager.build().authenticate(authReq);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

