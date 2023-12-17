package com.lms.isco.user.domain.services.generic;

import com.lms.isco.user.domain.models.request.LoginRequest;
import com.lms.isco.user.domain.models.request.RegisterRequest;
import com.lms.isco.user.domain.models.response.LoginResponse;
import com.lms.isco.user.domain.models.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerAdmin(RegisterRequest request);
    RegisterResponse registerTeacher(RegisterRequest request);
    RegisterResponse registerStudent(RegisterRequest request);
    LoginResponse login(LoginRequest request);

}
