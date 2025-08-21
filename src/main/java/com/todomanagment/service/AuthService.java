package com.todomanagment.service;

import com.todomanagment.dto.LoginDto;
import com.todomanagment.dto.RegisterDto;
import org.springframework.stereotype.Service;
@Service
public interface AuthService {
    String register( RegisterDto registerDto );
    String login( LoginDto loginDto );
}
