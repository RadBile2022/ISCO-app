package com.lms.isco.user.domain.services.generic;

import com.lms.isco.user.domain.models.request.LoginRequest;
import com.lms.isco.user.domain.models.request.RegisterRequest;
import com.lms.isco.user.domain.models.response.LoginResponse;
import com.lms.isco.user.domain.models.response.RegisterResponse;
import com.lms.isco.user.domain.repositories.generic.UserCredentialRepository;
import com.lms.isco.user.domain.services.AdminService;
import com.lms.isco.user.domain.services.StudentService;
import com.lms.isco.user.domain.services.TeacherService;
import com.lms.isco.user.domain.services.security.BCryptUtils;
import com.lms.isco.user.domain.services.security.JwtUtils;
import com.lms.isco.user.entity.Admin;
import com.lms.isco.user.entity.Student;
import com.lms.isco.user.entity.Teacher;
import com.lms.isco.user.entity.generic.Role;
import com.lms.isco.user.entity.generic.ERole;
import com.lms.isco.user.entity.generic.UserCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    final private UserCredentialRepository repository;
    final private BCryptUtils bCryptUtils;
    final private RoleService roleService;
    final private AdminService adminService;
    final private TeacherService teacherService;
    final private StudentService studentService;

    // TODO : For login method
    final private AuthenticationManager authenticationManager;
    final private JwtUtils jwtUtils;


    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerAdmin(RegisterRequest request) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_ADMIN);
            UserCredential credential = UserCredential.builder()
                    .email(request.getEmail())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .roles(List.of(role))
                    .build();

            repository.saveAndFlush(credential);


            String name = request.getEmail().split("@")[0];
            Admin admin = Admin.builder()
                    .name(name)
                    .email(request.getEmail())
                    .userCredential(credential)
                    .build();

            adminService.create(admin);

            return RegisterResponse.builder().email(credential.getEmail()).build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerTeacher(RegisterRequest request) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_TEACHER);
            UserCredential credential = UserCredential.builder()
                    .email(request.getEmail())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .roles(List.of(role))
                    .build();

            repository.saveAndFlush(credential);


            String name = request.getEmail().split("@")[0];
            Teacher teacher = Teacher.builder()
                    .name(name)
                    .email(request.getEmail())
                    .userCredential(credential)
                    .build();

            teacherService.create(teacher);

            return RegisterResponse.builder().email(credential.getEmail()).build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user is already exist cuy");
        }

    }

    @Override
    public RegisterResponse registerStudent(RegisterRequest request) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_STUDENT);
            UserCredential credential = UserCredential.builder()
                    .email(request.getEmail())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .roles(List.of(role))
                    .build();

            repository.saveAndFlush(credential);

            String name = request.getEmail().split("@")[0];


            Student student = Student.builder()
                    .name(name)
                    .email(request.getEmail())
                    .userCredential(credential)
                    .build();

            studentService.create(student);

            return RegisterResponse.builder().email(credential.getEmail()).build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user is already exist");
        }
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
           request.getEmail(),
           request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetailsImpl.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = jwtUtils.generateToken(userDetailsImpl.getEmail());

        return LoginResponse.builder()
                .email(userDetailsImpl.getEmail())
                .roles(roles)
                .token(token)
                .build();

    }
}
