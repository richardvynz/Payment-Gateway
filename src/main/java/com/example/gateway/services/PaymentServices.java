package com.example.gateway.services;

import com.example.gateway.DTOs.request.PaymentRequestDto;
import com.example.gateway.DTOs.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface PaymentServices {

    ResponseEntity<ApiResponse<String>> makePayment(PaymentRequestDto paymentRequestDto);
    ResponseEntity<ApiResponse<String>> refundPayment(PaymentRequestDto paymentRequestDto);
}
