package com.example.gateway.services.impl;

import com.example.gateway.DTOs.response.ApiResponse;
import com.example.gateway.enums.PaymentStatus;
import com.example.gateway.model.Transactions;
import com.example.gateway.model.WebHooks;
import com.example.gateway.repository.WebHooksRepository;
import com.example.gateway.services.WebhookServices;
import com.example.gateway.utils.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebhookServicesImpl implements WebhookServices {

    private final Logger logger = LoggerFactory.getLogger(WebhookServicesImpl.class);
    private final RestTemplate restTemplate;
    private final WebHooksRepository webHooksRepository;


    @Override
    public void sendWebhook(List<Transactions> transactionsList) {
        List<Transactions> pendingTransactions = transactionsList.stream()
                .filter(transaction -> transaction.getStatus().equals(PaymentStatus.PENDING))
                .collect(Collectors.toList());

        processTransactions(pendingTransactions);
    }

    private void processTransactions(List<Transactions> pendingTransactions) {
        String url = null;
        for (Transactions transactions : pendingTransactions) {
            WebHooks webHook = webHooksRepository.findByWalletId(transactions.getWalletId()).get();

            url = webHook.getWebhookUrl();

            if (url != null) {

                HttpEntity<Transactions> entity = new HttpEntity<>(transactions);

                try {
                    restTemplate.exchange(url, HttpMethod.POST, entity, ApiResponse.class);
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                }
            }
        }

    }
}
