package com.example.gateway.services.impl;

import com.example.gateway.DTOs.request.SignupDto;
import com.example.gateway.DTOs.request.LoginDto;
import com.example.gateway.DTOs.response.ApiResponse;
import com.example.gateway.services.UserServices;
import com.example.gateway.utils.AppUtil;
import com.example.gateway.exceptions.ValidationException;
import com.example.gateway.model.UserWallet;
import com.example.gateway.model.Users;
import com.example.gateway.repository.UserRepository;
import com.example.gateway.repository.UserWalletRepository;
import com.example.gateway.security.CustomUserDetailService;
import com.example.gateway.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserWalletRepository userWalletRepository;
    private final AppUtil appUtil;

    private final AuthenticationManager auth;
    private final JwtUtils jwtUtil;
    private final CustomUserDetailService userDetailsServices;


    @Override
    public ApiResponse<String> login(LoginDto loginDto) {

        if (!appUtil.validEmail(loginDto.getEmail()))
            throw new ValidationException("Invalid email");
        try {
            Authentication authentication = auth.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );


            if (authentication.isAuthenticated()) {
                Users user = userRepository.findByEmail(loginDto.getEmail())
                        .orElseThrow(() -> new ValidationException("Invalid login credentials"));

                String token = jwtUtil.generateToken(userDetailsServices.loadUserByUsername(user.getEmail()));
                return new ApiResponse<>(true,"login successful",token);


            } else {
                throw new ValidationException("Invalid username or password");
            }

        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<String> signUp(SignupDto signupDto) {

        if(userRepository.existsByEmail(signupDto.getEmail())) {
            throw new ValidationException("User already exist");
        }
        if (!appUtil.validEmail(signupDto.getEmail())) {
            throw new ValidationException("Invalid email");
        }
        if  (!signupDto.getConfirmPassword().equals(signupDto.getPassword())) {
            throw new ValidationException("Password mismatch");
        }

        Users user = Users.builder()
                .email(signupDto.getEmail())
                .firstName(signupDto.getFirstName())
                .lastName(signupDto.getLastName())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .role("USER")
                .build();

        user = userRepository.save(user);

        userWalletRepository.save(UserWallet.builder()
                        .userId(user.getUserId())
                        .balance(BigDecimal.ZERO)
                .build());

        return new ApiResponse<>(true,"signup successful", null);
    }
}
