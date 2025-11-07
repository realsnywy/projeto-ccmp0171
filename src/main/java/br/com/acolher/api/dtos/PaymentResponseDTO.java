package br.com.acolher.api.dtos;

import br.com.acolher.api.enums.PaymentMethod;
import br.com.acolher.api.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponseDTO(
        Long id,
        Long appointmentId,
        BigDecimal amount,
        PaymentStatus status,
        PaymentMethod method,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime paymentDate
) {
}
