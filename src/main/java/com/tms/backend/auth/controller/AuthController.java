package com.tms.backend.auth.controller;

import com.tms.backend.auth.request.LoginRequest;
import com.tms.backend.auth.service.AuthService;
import com.tms.backend.response.JwtResponse;
import com.tms.backend.response.Response;
import com.tms.backend.utils.ExceptionUtils;
import com.tms.backend.utils.ResponseUtils;
import com.tms.backend.validator.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest loginRequest, BindingResult bindingResult) {

        // Call the static validateRequest method
        RequestValidator.validateRequest(bindingResult);

        try {
            JwtResponse jwtResponse = authService.login(loginRequest);
            return ResponseUtils.data(jwtResponse);
        } catch (Exception exception) {
            return ExceptionUtils.handleException(exception);
        }
    }
}
