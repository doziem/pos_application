package com.doziem.pos.service;

import com.doziem.pos.request.UpdateUserRequest;
import com.doziem.pos.response.UserResponse;
import com.doziem.pos.system.Result;

public interface UserService {

    Result getUserById(String userId);
    Result getUserByEmail(String email);
    Result updateUser(String userId, UpdateUserRequest request);
    Result getUserFromToken(String token);
    Result getCurrentUser();
//    check user role by admin only
    Result changeUserRole(String userId, String role);

}
