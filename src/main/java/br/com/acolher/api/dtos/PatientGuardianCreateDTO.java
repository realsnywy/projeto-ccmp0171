package br.com.acolher.api.dtos;

public record PatientGuardianCreateDTO(
        String name,
        String cpf,
        String rg,
        String relationship,
        String telephone,
        String email
) {}
