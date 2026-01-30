package com.evangelism.api.dto.response;

import com.evangelism.api.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
    private Set<Role> roles;
    private String status;
    private CellResponse cell;
}
