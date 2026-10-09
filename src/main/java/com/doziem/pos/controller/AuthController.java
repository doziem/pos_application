package com.doziem.pos.controller;

import com.doziem.pos.request.LoginRequest;
import com.doziem.pos.request.UserRequest;
import com.doziem.pos.response.AuthResponse;
import com.doziem.pos.response.UserResponse;
import com.doziem.pos.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        AuthResponse authResponse = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request) {
        UserResponse userResponse = authService.register(request);
        return ResponseEntity.ok(userResponse);
    }


}
