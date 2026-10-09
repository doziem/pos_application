package com.doziem.pos.controller;

import com.doziem.pos.service.UserService;
import com.doziem.pos.system.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Result> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Result> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/current")
    public ResponseEntity<Result> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<Result> getUserFromToken(@PathVariable String token) {
        return ResponseEntity.ok(userService.getUserFromToken(token));
    }

    @PostMapping("/{userId}/role")
    public ResponseEntity<Result> changeUserRole(@PathVariable String userId, @RequestParam String role) {
        return ResponseEntity.ok(userService.changeUserRole(userId, role));
    }

}
