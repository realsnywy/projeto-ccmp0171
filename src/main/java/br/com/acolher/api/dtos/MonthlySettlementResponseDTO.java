package br.com.acolher.api.dtos;

import br.com.acolher.api.enums.SettlementStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MonthlySettlementResponseDTO(
        Long id,
        Long professionalId,
        int year,
        int month,
        BigDecimal totalReceived,
        BigDecimal settlementAmount,
        SettlementStatus status,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime paymentDate
) {}
