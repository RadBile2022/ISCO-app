package com.lms.isco.user.domain.services.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
// TODO : For Register
public class BCryptUtils {
    final private PasswordEncoder passwordEncoder;

    public String hashPassword(String password){
        return  passwordEncoder.encode(password);
    }
}
