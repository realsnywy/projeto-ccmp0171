package br.com.acolher.api.entities;

import br.com.acolher.api.enums.SettlementStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "monthly_settlements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySettlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "professional_id")
    private User professional;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private BigDecimal totalReceived;

    @Column(nullable = false)
    private BigDecimal settlementAmount;

    @Enumerated(EnumType.STRING)
    private SettlementStatus status;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

}
