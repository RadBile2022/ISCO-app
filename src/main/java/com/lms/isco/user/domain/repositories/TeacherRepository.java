package com.lms.isco.user.domain.repositories;

import com.lms.isco.user.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,String> {
}
