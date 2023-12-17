package com.lms.isco.user.controllers;

import com.lms.isco.common.CommonResponse;
import com.lms.isco.user.domain.models.request.LoginRequest;
import com.lms.isco.user.domain.models.request.RegisterRequest;
import com.lms.isco.user.domain.models.response.LoginResponse;
import com.lms.isco.user.domain.models.response.RegisterResponse;
import com.lms.isco.user.domain.services.generic.AuthService;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    final private AuthService authService;

    @PostMapping(path = "/register-admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequest request){
        RegisterResponse register = authService.registerAdmin(request);
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully registered")
                .data(register)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    @PostMapping(path = "/register-teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody RegisterRequest request){
        RegisterResponse register = authService.registerTeacher(request);
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully registered")
                .data(register)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    @PostMapping(path = "/register-student")
    public ResponseEntity<?> registerStudent(@RequestBody RegisterRequest request){
        RegisterResponse register = authService.registerStudent(request);
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully registered")
                .data(register)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginResponse login = authService.login(request);
        CommonResponse<Object> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully login")
                .data(login)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
