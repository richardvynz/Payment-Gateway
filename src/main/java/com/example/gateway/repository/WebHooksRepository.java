package com.example.gateway.repository;

import com.example.gateway.model.WebHooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebHooksRepository extends JpaRepository<WebHooks, Long> {

    Optional<WebHooks> findByWalletId(Long walletId);
}
