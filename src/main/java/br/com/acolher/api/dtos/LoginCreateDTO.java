package br.com.acolher.api.dtos;

import jakarta.validation.constraints.NotEmpty;

public record LoginCreateDTO(@NotEmpty(message = "Email é obrigatório") String email,
                             @NotEmpty(message = "Senha é obrigatória") String password) {
}
