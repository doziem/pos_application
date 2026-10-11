package com.doziem.pos.mapper;

import com.doziem.pos.model.User;
import com.doziem.pos.request.UpdateUserRequest;
import com.doziem.pos.request.UserRequest;
import com.doziem.pos.response.UserResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class UserMapper {

    public static User toEntity(UserRequest request) {
        return User.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .build();
    }

    public static  UserRequest toDto(User user) {
        return UserRequest.builder()
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .lastLogin(user.getLastLogin())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .lastLogin(user.getLastLogin())
                .build();
    }

    public static User updateToResponse(User user, UpdateUserRequest request) {
        return User.builder()
                .userId(user.getUserId())
                .email(request.getEmail() != null ? request.getEmail() : user.getEmail())
                .phoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : user.getPhoneNumber())
                .role(request.getRole() != null ? request.getRole() : user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(ZonedDateTime.now())
                .lastLogin(user.getLastLogin())
                .build();
    }

}