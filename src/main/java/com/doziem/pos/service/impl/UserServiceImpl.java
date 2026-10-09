package com.doziem.pos.service.impl;

import com.doziem.pos.configuration.JwtProvider;
import com.doziem.pos.enums.UserRole;
import com.doziem.pos.exception.ResourceNotFoundException;
import com.doziem.pos.exception.UnauthorizedException;
import com.doziem.pos.mapper.UserMapper;
import com.doziem.pos.model.User;
import com.doziem.pos.repository.UserRepository;
import com.doziem.pos.request.UpdateUserRequest;
import com.doziem.pos.response.UserResponse;
import com.doziem.pos.service.UserService;
import com.doziem.pos.system.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public Result getUserById(String userId) {

        UserResponse response = userRepository.findById(userId)
                .map(user -> UserResponse.builder()
                        .userId(user.getUserId())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return Result.builder()
                .success(true)
                .message("User retrieved successfully")
                .data(response)
                .build();
    }

    @Override
    public Result getUserByEmail(String email) {
        UserResponse response = userRepository.findByEmail(email)
                .map(user -> UserResponse.builder()
                        .userId(user.getUserId())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return Result.builder()
                .success(true)
                .message("User retrieved successfully")
                .data(response)
                .build();

    }

    @Override
    public Result updateUser(String userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(request.getRole().equals(UserRole.ROLE_ADMIN) && !user.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new IllegalArgumentException("Cannot change role to ADMIN");
        }
        User updatedUser = UserMapper.updateToResponse(user, request);

        return Result.builder()
                .success(true)
                .message("User updated successfully")
                .data(UserMapper.toResponse(userRepository.save(updatedUser)))
                .build();
    }

    @Override
    public Result getUserFromToken(String token) {
        String email = jwtProvider.getEmailFromToken(token);
        return getUserByEmail(email);
    }

    @Override
    public Result getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedException("User is not authenticated");
        }

        String email = authentication.getName();

        return getUserByEmail(email);
    }

    @Override
    public Result changeUserRole(String userId, String role) {

        Result currentUser = getCurrentUser();
        if (!((UserResponse) currentUser.getData()).getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new UnauthorizedException("Only admin can change user role");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setRole(UserRole.valueOf(role));
        user.setUpdatedAt(ZonedDateTime.now());

        return Result.builder()
                .success(true)
                .message("User role changed successfully")
                .data(UserMapper.toResponse(userRepository.save(user)))
                .build();
    }
}
