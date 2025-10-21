package br.com.acolher.api.dtos;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


public record PatientRecordResponseDTO(
        Long id,
        Long appointmentId,
        String appointmentSummary,
        @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime recordDate
) {
}
