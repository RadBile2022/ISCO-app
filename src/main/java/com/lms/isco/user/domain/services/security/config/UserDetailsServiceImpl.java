package com.lms.isco.user.domain.services.security.config;

import com.lms.isco.user.domain.repositories.generic.UserCredentialRepository;
import com.lms.isco.user.domain.services.generic.UserDetailsImpl;
import com.lms.isco.user.entity.generic.UserCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// TODO : Require for AuthRequestFilter
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    final private UserCredentialRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredential userCredential = repository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        List<SimpleGrantedAuthority> grantedAuthorities = userCredential.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());

        return UserDetailsImpl.builder()
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .authorities(grantedAuthorities)
                .build();
    }
}
