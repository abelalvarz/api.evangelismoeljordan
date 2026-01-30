package com.evangelism.api.service;

import com.evangelism.api.dto.response.LoginResponse;
import com.evangelism.api.dto.response.UserResponse;
import com.evangelism.api.dto.request.LoginRequest;
import com.evangelism.api.dto.request.RegisterRequest;
import com.evangelism.api.entity.User;
import com.evangelism.api.mappers.UserMapper;
import com.evangelism.api.security.CustomUserDetails;
import com.evangelism.api.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final CellService cellService;
    private final UserService userService;
    private final UserMapper userMapper;

    public LoginResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);
        User user = userDetails.getUser();

        return new LoginResponse(
                jwt,
                user.getId(),
                user.getEmail(),
                user.getRolesList(),
                cellService.findCellByUserAndRole(user)
        );
    }

    public UserResponse register(RegisterRequest registerRequest){
        return userService.createUser(registerRequest);
    }
}
