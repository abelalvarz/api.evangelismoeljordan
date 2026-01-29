package com.evangelism.api.dto.request;

import com.evangelism.api.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class RegisterRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private Set<Role> roles;
    private java.util.UUID cellId;
}
