package com.lms.isco.user.domain.services.generic;

import com.lms.isco.user.entity.generic.ERole;
import com.lms.isco.user.entity.generic.Role;

public interface RoleService {
    Role getOrSave(ERole eRole);
}
