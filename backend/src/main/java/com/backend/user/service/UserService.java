package com.backend.user.service;

import com.backend.user.dto.LoginRequest;
import com.backend.user.dto.LoginResponse;
import com.backend.user.dto.Register;
import com.backend.user.dto.UserProfile;

public interface UserService {
    UserProfile register(Register register) throws Exception;

    LoginResponse login(LoginRequest loginRequest) throws Exception;
}
