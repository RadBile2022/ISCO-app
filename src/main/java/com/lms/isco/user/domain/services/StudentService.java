package com.lms.isco.user.domain.services;

import com.lms.isco.user.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
    Student create(Student student);
// TODO : CRUD nanti, sekaran create dulu
//    Page<StudentResponse> getAll(Integer page, Integer size);
//
//    Student getById(String id);
//
//    StudentResponse update(StudentRequest request);


}
