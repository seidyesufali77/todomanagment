package com.todomanagment.config;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@AllArgsConstructor
public class SpringSecurityConfig {
    private UserDetailsService userDetailsService;
    @Bean
    SecurityFilterChain securityFilterChain( HttpSecurity httpSecurity  )throws Exception{
        httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests((authorize)->{
//                    authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("USER","ADMIN");
                    authorize.anyRequest().authenticated(); // All other requests require authentication
                }).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration configuration ) throws Exception{
        return configuration.getAuthenticationManager();
    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails seid=User.builder().username("seid").password(passwordEncoder().encode("seid123")) // {noop} indicates that no password encoder is used
//                .roles("USER").build();
//        UserDetails admin=User.builder().username("admin").password(passwordEncoder().encode("admin123") )// {noop} indicates that no password encoder is used
//                .roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(seid,admin);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
