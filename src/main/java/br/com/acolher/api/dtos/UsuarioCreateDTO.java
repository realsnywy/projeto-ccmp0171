package br.com.acolher.api.dtos;

import br.com.acolher.api.enums.TipoUsuario;

public record UsuarioCreateDTO(
        String nome,
        String email,
        String senha,
        String CPF,
        String RG,
        String telefone,
        String especialidade,
        String registroProfissional,
        TipoUsuario tipoUsuario
        ) {}
