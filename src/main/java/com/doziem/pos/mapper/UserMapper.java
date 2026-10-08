package com.doziem.pos.mapper;

import com.doziem.pos.model.User;
import com.doziem.pos.request.UserRequest;
import com.doziem.pos.response.UserResponse;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UserMapper {

    public static User toEntity(UserRequest request) {
        return User.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
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

}