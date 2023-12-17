package com.lms.isco.user.domain.services;

import com.lms.isco.user.domain.repositories.AdminRepository;
import com.lms.isco.user.domain.repositories.TeacherRepository;
import com.lms.isco.user.entity.Admin;
import com.lms.isco.user.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    final private TeacherRepository repository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Teacher create(Teacher teacher){
        try {
            return repository.save(teacher);
        } catch (DataIntegrityViolationException e){
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "username already exist");
        }

    }
}
