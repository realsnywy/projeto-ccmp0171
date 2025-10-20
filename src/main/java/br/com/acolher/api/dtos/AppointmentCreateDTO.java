package br.com.acolher.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AppointmentCreateDTO(
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime appointmentDate,
        Long patientId,
        Long professionalId
) {}
