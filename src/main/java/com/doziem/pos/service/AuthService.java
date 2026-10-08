package com.doziem.pos.service;

import com.doziem.pos.request.UserRequest;
import com.doziem.pos.response.AuthResponse;
import com.doziem.pos.response.UserResponse;

public interface AuthService {

    AuthResponse login(String email, String password);
    UserResponse register(UserRequest request);
}
