package br.com.acolher.api.dtos;

import br.com.acolher.api.enums.UserType;

public record UserCreateDTO(
        String name,
        String email,
        String password,
        String CPF,
        String RG,
        String telephone,
        String speciality,
        String professionalRegister,
        UserType userType
        ) {}
