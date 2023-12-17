package com.lms.isco.user.domain.services;

import com.lms.isco.user.domain.repositories.AdminRepository;
import com.lms.isco.user.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    final private AdminRepository repository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Admin create(Admin admin){
        try {
            return repository.save(admin);
        } catch (DataIntegrityViolationException e){
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "username already exist");
        }

    }
}
