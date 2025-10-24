package br.com.acolher.api.dtos;

import br.com.acolher.api.enums.PaymentMethod;
import br.com.acolher.api.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record PaymentCreateDTO(
        Long appointmentId,
        PaymentStatus status,
        PaymentMethod method,
        @JsonFormat (pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime paymentDate
) {
}
