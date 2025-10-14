package br.com.acolher.api.dtos;

import br.com.acolher.api.enums.TipoUsuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String senha,
        String CPF,
        String RG,
        String telefone,
        String especialdade,
        String registroProfissional,
        TipoUsuario tipoUsuario
) {}
