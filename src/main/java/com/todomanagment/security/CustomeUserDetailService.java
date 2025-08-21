package com.todomanagment.security;
import com.todomanagment.entity.User;
import com.todomanagment.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class CustomeUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail) //
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username or email: " + usernameOrEmail));
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())) // e.g. ROLE_ADMIN or USER_WRITE
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
               usernameOrEmail,   // better: canonical username
                user.getPassword(),
                authorities
        );
    }
}
