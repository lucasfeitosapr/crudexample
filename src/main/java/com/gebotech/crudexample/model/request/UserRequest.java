package com.gebotech.crudexample.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gebotech.crudexample.model.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Builder
@Getter
public class UserRequest {

    private String username;
    private String email;
    private String password;
    private Set<String> roles;


}
