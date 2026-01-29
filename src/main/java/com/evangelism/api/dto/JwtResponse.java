package com.evangelism.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private UUID id;
    private String email;
    private Set<String> roles;
    private CellSummaryDTO cell;
}
