package br.com.acolher.api.dtos;

public record PatientGuardianUpdateDTO(
        Long id,
        String name,
        String cpf,
        String rg,
        String relationship,
        String telephone,
        String email
) {}
