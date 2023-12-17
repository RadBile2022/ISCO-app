package com.lms.isco.user.entity.generic;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum ERole {
    ROLE_ADMIN,
    ROLE_TEACHER,
    ROLE_STUDENT;

    public static ERole get (String value){
        for(ERole eRole : ERole.values()){
            if (eRole.name().equalsIgnoreCase(value)) return eRole;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "role not found");
    }
}
