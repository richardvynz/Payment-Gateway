package com.example.gateway.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RestTemplateConfig {
    @Bean
    public org.springframework.web.client.RestTemplate getRestTemplate() {
        return new org.springframework.web.client.RestTemplate();
    }
}
