package com.lms.isco.user.domain.repositories;

import com.lms.isco.user.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
}
