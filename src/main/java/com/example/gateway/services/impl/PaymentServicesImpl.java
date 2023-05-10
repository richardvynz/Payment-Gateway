package com.example.gateway.services.impl;

import com.example.gateway.DTOs.request.PaymentRequestDto;
import com.example.gateway.DTOs.response.ApiResponse;
import com.example.gateway.services.PaymentServices;
import com.example.gateway.utils.AppUtil;
import com.example.gateway.enums.PaymentStatus;
import com.example.gateway.model.UserWallet;
import com.example.gateway.model.Users;
import com.example.gateway.repository.UserWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServicesImpl implements PaymentServices {

    private final AppUtil appUtil;
    private final UserWalletRepository userWalletRepository;
    @Override
    public ResponseEntity<ApiResponse<String>> makePayment(PaymentRequestDto paymentRequestDto) {

        UserWallet userWallet = null;
        Users user = null;
        try {
            user = appUtil.getLoggedInUser();
            String email = user.getEmail();
            userWallet = userWalletRepository.findByUserId(user.getUserId()).orElse(UserWallet.builder()
                    .userId(user.getUserId())
                    .balance(BigDecimal.ZERO)
                    .build());

        } catch (Exception ex) {

            return new ResponseEntity<>(new ApiResponse<>(false, PaymentStatus.FAILED.name(), ex.getMessage()),HttpStatus.BAD_REQUEST);
        }

        userWallet.setBalance(userWallet.getBalance().add(paymentRequestDto.getAmount()));

        userWalletRepository.save(userWallet);

        return new ResponseEntity<>(new ApiResponse<>(true, PaymentStatus.SUCCESSFUL.name(), "Account funded successfully"),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<String>> refundPayment(PaymentRequestDto paymentRequestDto) {
        UserWallet wallet = null;
        Users user = null;

        try {
            user = appUtil.getLoggedInUser();
            String email = user.getEmail();
            wallet = userWalletRepository.findByUserId(user.getUserId()).orElse(UserWallet.builder()
                    .userId(user.getUserId())
                    .balance(BigDecimal.ZERO)
                    .build());

        } catch (Exception ex) {

            return new ResponseEntity<>(new ApiResponse<>(false, PaymentStatus.FAILED.name(), ex.getMessage()),HttpStatus.BAD_REQUEST);
        }

        if (paymentRequestDto.getAmount()
                .compareTo(wallet.getBalance()) >=0) {
            return new ResponseEntity<>(new ApiResponse<>(false,
                    PaymentStatus.FAILED.name(),
                    "Insufficient balance"),
                    HttpStatus.BAD_REQUEST);
        }

        wallet.setBalance(wallet
                .getBalance()
                .subtract(paymentRequestDto.getAmount()));

        userWalletRepository.save(wallet);

        return new ResponseEntity<>(new ApiResponse<>(true,
                PaymentStatus.SUCCESSFUL.name(),
                "Refund was successful"),HttpStatus.OK);
    }
}
