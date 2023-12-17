package com.lms.isco.user.domain.repositories.generic;

import com.lms.isco.user.entity.generic.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lms.isco.user.entity.generic.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByRole(ERole role);

}
