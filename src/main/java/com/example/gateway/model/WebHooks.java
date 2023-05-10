package com.example.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebHooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long webhookId;
    @Column(nullable = false, unique = true)
    private Long walletId;
    @Column(nullable = false, unique = true)
    private String webhookUrl;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
