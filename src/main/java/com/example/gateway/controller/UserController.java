package com.example.gateway.controller;

import com.example.gateway.DTOs.request.SignupDto;
import com.example.gateway.DTOs.request.LoginDto;
import com.example.gateway.DTOs.response.ApiResponse;
import com.example.gateway.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserServices userServices;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody SignupDto signupDto) {
        return ResponseEntity.ok(userServices.signUp(signupDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userServices.login(loginDto));
    }
}
