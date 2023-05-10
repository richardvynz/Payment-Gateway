package com.example.gateway.services;

import com.example.gateway.DTOs.request.SignupDto;
import com.example.gateway.DTOs.request.LoginDto;
import com.example.gateway.DTOs.response.ApiResponse;

public interface UserServices {

    ApiResponse<String> login(LoginDto loginDto);
    ApiResponse<String> signUp(SignupDto signupDto);
}
