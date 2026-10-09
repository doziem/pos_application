package com.doziem.pos.request;

import com.doziem.pos.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@Data
@Builder
public class UpdateUserRequest {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern( regexp = "^[0-9+\\-() ]{7,20}$", message = "Enter a valid phone number" )
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern( regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#]).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character" )
    private String password;

    @NotNull(message = "Role is required")
    private UserRole role;

    private ZonedDateTime updatedAt;
}
