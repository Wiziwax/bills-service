package org.interswitch.billsservice.Entities;

import jakarta.persistence.*;
import lombok.Data;
//import org.interswitch.onboardingservice.Entities.Accounts;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "account_id", nullable = false)
//    private Accounts account;

    @Column
    private Long productId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    // Getters and Setters
}
