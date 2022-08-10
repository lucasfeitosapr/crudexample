package com.gebotech.crudexample.model.response;

import com.gebotech.crudexample.model.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Builder
@Getter
public class UserResponse {
    private String username;
    private String email;
    private Set<Role> roles;
}
