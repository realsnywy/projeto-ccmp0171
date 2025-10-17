package br.com.acolher.api.dtos;

import br.com.acolher.api.enums.UserType;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String password,
        String cpf,
        String rg,
        String telephone,
        String speciality,
        String professionalRegister,
        UserType userType
) {}
