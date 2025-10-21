package br.com.acolher.api.dtos;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public record PatientRecordCreateDTO(
        Long appointmentId,
        String appointmentSummary,
        @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime recordDate,
        MultipartFile file
) {
}
