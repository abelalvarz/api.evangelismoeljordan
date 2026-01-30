package com.evangelism.api.service;

import com.evangelism.api.dto.response.UserResponse;
import com.evangelism.api.dto.request.RegisterRequest;
import com.evangelism.api.entity.Cell;
import com.evangelism.api.entity.Role;
import com.evangelism.api.entity.User;
import com.evangelism.api.exceptions.ResourceNotFoundException;
import com.evangelism.api.mappers.UserMapper;
import com.evangelism.api.repository.CellRepository;
import com.evangelism.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CellRepository cellRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(RegisterRequest registerRequest){

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        User user = userMapper.toEntityFromCreationRequest(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        User savedUser = userRepository.save(user);

        if (!registerRequest.isCellUser()) {
            return userMapper.toResponseDto(user, null);
        }
        Cell assignedCell = assignUserToCell(savedUser, registerRequest.getCellId());
        return userMapper.toResponseDto(user, assignedCell);
    }

    public User findById(UUID id) {

        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserResponse findByUserId(UUID id){
        User user = findById(id);
        if(user.isAdmin())
            return userMapper.toResponseDto(user, null);

        Cell cell = cellRepository.findByTeacherOrSecretary(user)
                .orElseThrow(() -> new ResourceNotFoundException("User is not assigned to any cell:" + id));

        return userMapper.toResponseDto(user, cell);
    }

    private Cell assignUserToCell(User user, UUID cellId) {
        Cell cell = cellRepository.findById(cellId)
                .orElseThrow(() -> new ResourceNotFoundException("Cell not found with ID: " + cellId));

        if (user.getRoles().contains(Role.TEACHER)) {
            isAvailableRole(cell, Role.TEACHER);
            cell.setTeacher(user);
        } else if (user.getRoles().contains(Role.SECRETARY)) {
            isAvailableRole(cell, Role.SECRETARY);
            cell.setSecretary(user);
        }
        return cellRepository.save(cell);
    }
    private void isAvailableRole(Cell cell, Role role){
        if(cell.getTeacher() != null &&  role == Role.TEACHER){
            throw new RuntimeException("Cell already have a teacher");
        }
        if (cell.getSecretary() != null && role == Role.SECRETARY){
            throw new RuntimeException("Cell already have a secretary");
        }
    }

}

