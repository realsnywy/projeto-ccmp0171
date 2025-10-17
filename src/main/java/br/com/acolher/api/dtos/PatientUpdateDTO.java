package br.com.acolher.api.dtos;

import br.com.acolher.api.enums.PatientStatus;

import java.time.LocalDate;

public record PatientUpdateDTO(
        Long id,
        String name,
        char sex,
        LocalDate birthDate,
        String cpf,
        String rg,
        String telephone,
        String email,
        PatientStatus status,
        boolean isDependent,
        Long guardianId
) {}
