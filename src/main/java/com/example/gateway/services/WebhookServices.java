package com.example.gateway.services;

import com.example.gateway.DTOs.response.ApiResponse;
import com.example.gateway.model.Transactions;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WebhookServices {
    void sendWebhook(List<Transactions> transactionsList);
}
