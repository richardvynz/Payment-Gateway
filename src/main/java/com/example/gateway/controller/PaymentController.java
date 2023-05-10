package com.example.gateway.controller;

import com.example.gateway.DTOs.request.PaymentRequestDto;
import com.example.gateway.DTOs.response.ApiResponse;
import com.example.gateway.services.PaymentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment")
public class PaymentController {

    private final PaymentServices paymentServices;

    @PostMapping("/payment-request")
    public ResponseEntity<ApiResponse<String>> paymentRequest(@Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        return paymentServices.makePayment(paymentRequestDto);
    }

    @PostMapping("/payment-refund")
    public ResponseEntity<ApiResponse<String>> refundPayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        return paymentServices.refundPayment(paymentRequestDto);
    }
}
