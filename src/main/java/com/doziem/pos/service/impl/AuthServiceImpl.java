package com.doziem.pos.service.impl;

import com.doziem.pos.configuration.JwtProvider;
import com.doziem.pos.enums.UserRole;
import com.doziem.pos.exception.InvalidCredentialsException;
import com.doziem.pos.exception.UserAlreadyExistsException;
import com.doziem.pos.mapper.UserMapper;
import com.doziem.pos.model.User;
import com.doziem.pos.repository.UserRepository;
import com.doziem.pos.request.UserRequest;
import com.doziem.pos.response.AuthResponse;
import com.doziem.pos.response.UserResponse;
import com.doziem.pos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserService customUserService;

    private static final String INVALID_CREDENTIALS = "Invalid email or password";

    @Override
    @Transactional
    public AuthResponse login(String email, String password) {
        if (email == null || password == null) {
            throw new InvalidCredentialsException(INVALID_CREDENTIALS);
        }

        User user = userRepository.findByEmail(email)
                .filter(found -> passwordEncoder.matches(password, found.getPassword()))
                .orElseThrow(() -> new InvalidCredentialsException(INVALID_CREDENTIALS));

        UserDetails userDetails = customUserService.toUserDetails(user);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        user.setLastLogin(ZonedDateTime.now());

        return AuthResponse.builder()
                .token(jwtProvider.generateToken(authentication))
                .message("Login successful")
                .user(UserMapper.toResponse(userRepository.save(user)))
                .build();
    }

    @Override
    @Transactional
    public UserResponse register(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        if (request.getRole() == UserRole.ROLE_ADMIN) {
            throw new UserAlreadyExistsException("Cannot register as admin");
        }

        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        ZonedDateTime now = ZonedDateTime.now();
        user.setCreatedAt(now);
        user.setLastLogin(null);
        user.setUpdatedAt(null);

        return UserMapper.toResponse(userRepository.save(user));
    }
}
