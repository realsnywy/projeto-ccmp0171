package br.com.acolher.api.dtos;


import java.time.LocalDate;

public record PatientCreateDTO(
        String name,
        char sex,
        LocalDate birthDate,
        String cpf,
        String rg,
        String telephone,
        String email,
        boolean isDependent,
        Long guardianId
) {}
