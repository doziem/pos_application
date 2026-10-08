package com.doziem.pos.response;

import com.doziem.pos.enums.UserRole;
import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String userId;

    private String email;

    private String phoneNumber;

    private UserRole role;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime lastLogin;
}
