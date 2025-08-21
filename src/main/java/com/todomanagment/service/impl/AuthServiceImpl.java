package com.todomanagment.service.impl;

import com.todomanagment.dto.LoginDto;
import com.todomanagment.dto.RegisterDto;
import com.todomanagment.entity.Role;
import com.todomanagment.entity.User;
import com.todomanagment.exception.TodoApiException;
import com.todomanagment.repository.RoleRepository;
import com.todomanagment.repository.UserRepository;
import com.todomanagment.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
 private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;
    @Override
    public String register( RegisterDto registerDto ){
    // Check if the username already exists in the database
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw  new TodoApiException(HttpStatus.BAD_REQUEST,"Username already exists");
        }
    // Check if the email already exists in the database
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw  new TodoApiException(HttpStatus.BAD_REQUEST,"Email already exists");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "User role not found");
        }
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "The user has been registered successfully";
    }

    @Override
    public String login( LoginDto loginDto ){
       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginDto.getUsernameOrEmail(),loginDto.getPassword()));
       SecurityContextHolder.getContext().setAuthentication(authentication);
        return "user logged in successfully";
    }
}
