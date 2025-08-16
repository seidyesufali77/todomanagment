package com.todomanagment.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SpringSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain( HttpSecurity httpSecurity  )throws Exception{
        httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests((authorize)->{
                    authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
                    authorize.anyRequest().authenticated(); // All other requests require authentication
                }).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails seid=User.builder().username("seid").password(passwordEncoder().encode("seid123")) // {noop} indicates that no password encoder is used
                .roles("USER").build();
        UserDetails admin=User.builder().username("admin").password(passwordEncoder().encode("admin123") )// {noop} indicates that no password encoder is used
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(seid,admin);
    }
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
