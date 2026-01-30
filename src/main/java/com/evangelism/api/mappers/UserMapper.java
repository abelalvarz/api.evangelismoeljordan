package com.evangelism.api.mappers;

import com.evangelism.api.dto.response.CellResponse;
import com.evangelism.api.dto.response.UserResponse;
import com.evangelism.api.dto.request.RegisterRequest;
import com.evangelism.api.entity.Cell;
import com.evangelism.api.entity.Role;
import com.evangelism.api.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final CellMapper cellMapper;

    public User toEntityFromCreationRequest(RegisterRequest registerRequest){
        User user = new User();
        user.setFirebaseId(registerRequest.getId());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getPhone());
        user.setPassword(registerRequest.getPassword());
        user.setRoles(registerRequest.getRoles() != null ? registerRequest.getRoles() : Set.of(Role.TEACHER));
        return user;
    }
    public UserResponse toResponseDto(User user, Cell cell){
        CellResponse cellDto = (cell != null) ? cellMapper.toCellResponse(cell) : null;
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhone())
                .roles(user.getRoles())
                .status(user.getStatus())
                .cell(cellDto)
                .build();
    }
}
