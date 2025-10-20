package br.com.acolher.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
        Long id,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime issueDate,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime appointmentDate,
        Long patientId,
        Long professionalId
) {
}
