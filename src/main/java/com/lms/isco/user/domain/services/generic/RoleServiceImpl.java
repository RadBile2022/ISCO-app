package com.lms.isco.user.domain.services.generic;

import com.lms.isco.user.domain.repositories.generic.RoleRepository;
import com.lms.isco.user.entity.generic.ERole;
import com.lms.isco.user.entity.generic.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    final private RoleRepository repository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Role getOrSave(ERole eRole) {
        return repository.findByRole(eRole).orElseGet(()->repository.save(Role.builder().role(eRole).build()));
    }


}
