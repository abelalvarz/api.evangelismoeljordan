package com.evangelism.api.mappers;

import com.evangelism.api.dto.UserResponseDTO;
import com.evangelism.api.dto.request.RegisterRequest;
import com.evangelism.api.entity.Role;
import com.evangelism.api.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserMapper {

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
    public UserResponseDTO toResponseDto(User user){
        return new UserResponseDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(),user.getRoles(), null);
    }
}
