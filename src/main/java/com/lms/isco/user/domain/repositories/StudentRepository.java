package com.lms.isco.user.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<com.lms.isco.user.entity.Student,String> {
}
