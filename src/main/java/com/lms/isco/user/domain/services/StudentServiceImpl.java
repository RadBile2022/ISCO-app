package com.lms.isco.user.domain.services;

import com.lms.isco.user.domain.repositories.StudentRepository;
import com.lms.isco.user.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    final private StudentRepository repository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Student create(Student student) {
        try {
            return repository.save(student);
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"username already exist");
        }
    }
}
