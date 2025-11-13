package br.com.acolher.api.dtos;

import br.com.acolher.api.enums.SettlementStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MonthlySettlementUpdateDTO(
        Long id,
        BigDecimal totalReceived,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime paymentDate
) {
}
