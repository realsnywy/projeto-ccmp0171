package br.com.acolher.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AppointmentUpdateDTO(
        Long id,
        Long patientId,
        Long professionalId,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime appointmentDate,
        BigDecimal amount
) {}
