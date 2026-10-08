package com.doziem.pos.service.impl;

import com.doziem.pos.configuration.JwtProvider;
import com.doziem.pos.enums.UserRole;
import com.doziem.pos.exception.InvalidCredentialsException;
import com.doziem.pos.exception.ResourceNotFoundException;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;

import static com.stripe.net.OAuth.token;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserService customUserService;

    @Override
    public AuthResponse login(String email, String password) {

     User user =   userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Authentication authentication = authenticate(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        assert authentication != null;
        String token = jwtProvider.generateToken(authentication);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String role = authorities.iterator().next().getAuthority();

        user.setLastLogin(ZonedDateTime.now());
        ;

        return AuthResponse.builder()
                .token(token)
                .message("Login successful")
                .user(UserMapper.toResponse(userRepository.save(user)))
                .build();
    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails = customUserService.loadUserByUsername(email);

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public UserResponse register(UserRequest request) {
         userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserAlreadyExistsException("Email already exists"));

        if(request.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new UserAlreadyExistsException("Cannot register as admin");
        }

        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(ZonedDateTime.now());
        user.setLastLogin(ZonedDateTime.now());
        user.setUpdatedAt(null);

        return UserMapper.toResponse(userRepository.save(user));
    }
}
