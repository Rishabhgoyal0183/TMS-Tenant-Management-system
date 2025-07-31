package com.tms.backend.auth.service;

import com.tms.backend.auth.request.LoginRequest;
import com.tms.backend.response.JwtResponse;

public interface AuthService {

    JwtResponse login(LoginRequest loginRequest);

    void logout(String token);

}
