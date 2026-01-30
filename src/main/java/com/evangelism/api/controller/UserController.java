package com.evangelism.api.controller;

import com.evangelism.api.converter.ResponseConverter;
import com.evangelism.api.security.CustomUserDetails;
import com.evangelism.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseConverter responseConverter;

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseEntity.ok(
                responseConverter.convert(userService.findByUserId(userDetails.getUser().getId())));
    }
}
