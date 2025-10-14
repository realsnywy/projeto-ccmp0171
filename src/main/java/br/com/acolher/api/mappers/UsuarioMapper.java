package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.UsuarioCreateDTO;
import br.com.acolher.api.dtos.UsuarioResponseDTO;
import br.com.acolher.api.dtos.UsuarioUpdateDTO;
import br.com.acolher.api.entities.DiretorGeral;
import br.com.acolher.api.entities.Profissional;
import br.com.acolher.api.entities.Recepcionista;
import br.com.acolher.api.entities.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioCreateDTO usuarioDTO) {
        Usuario usuario = null;
        switch (usuarioDTO.tipoUsuario()) {
            case DIRETOR_GERAL -> {
                DiretorGeral diretorGeral = new DiretorGeral();
                diretorGeral.setNome(usuarioDTO.nome());
                diretorGeral.setEmail(usuarioDTO.email());
                diretorGeral.setSenha(usuarioDTO.senha());
                diretorGeral.setCPF(usuarioDTO.CPF());
                diretorGeral.setRG(usuarioDTO.RG());
                diretorGeral.setTelefone(usuarioDTO.telefone());
                usuario = diretorGeral;
            }
            case RECEPCIONISTA -> {
                Recepcionista recepcionista = new Recepcionista();
                recepcionista.setNome(usuarioDTO.nome());
                recepcionista.setEmail(usuarioDTO.email());
                recepcionista.setSenha(usuarioDTO.senha());
                recepcionista.setCPF(usuarioDTO.CPF());
                recepcionista.setRG(usuarioDTO.RG());
                recepcionista.setTelefone(usuarioDTO.telefone());
                usuario = recepcionista;
            }
            case PROFISSIONAL -> {
                Profissional profissional = new Profissional();
                profissional.setNome(usuarioDTO.nome());
                profissional.setEmail(usuarioDTO.email());
                profissional.setSenha(usuarioDTO.senha());
                profissional.setCPF(usuarioDTO.CPF());
                profissional.setRG(usuarioDTO.RG());
                profissional.setTelefone(usuarioDTO.telefone());
                profissional.setEspecialidade(usuarioDTO.especialidade());
                profissional.setRegistroProfissional(usuarioDTO.registroProfissional());
                usuario = profissional;
            }
            default -> throw new IllegalArgumentException("Tipo de usuário inválido: " + usuarioDTO.tipoUsuario());
        }
        return usuario;
    }

    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        if(usuario instanceof Profissional) {
            Profissional profissional = (Profissional) usuario;
            return new UsuarioResponseDTO(profissional.getId(), profissional.getNome(), profissional.getEmail(), profissional.getSenha(), profissional.getCPF(), profissional.getRG(), profissional.getTelefone(), profissional.getEspecialidade(), profissional.getRegistroProfissional(), profissional.getTipoUsuario());
        }else
            return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCPF(), usuario.getRG(), usuario.getTelefone(), null, null, usuario.getTipoUsuario());
    }

    public static Usuario updateEntity(UsuarioUpdateDTO usuarioDTO) {
        Usuario usuario = null;
        switch (usuarioDTO.tipoUsuario()) {
            case DIRETOR_GERAL -> {
                DiretorGeral diretorGeral = new DiretorGeral();
                diretorGeral.setNome(usuarioDTO.nome());
                diretorGeral.setEmail(usuarioDTO.email());
                diretorGeral.setSenha(usuarioDTO.senha());
                diretorGeral.setCPF(usuarioDTO.CPF());
                diretorGeral.setRG(usuarioDTO.RG());
                diretorGeral.setTelefone(usuarioDTO.telefone());
                usuario = diretorGeral;
            }
            case RECEPCIONISTA -> {
                Recepcionista recepcionista = new Recepcionista();
                recepcionista.setNome(usuarioDTO.nome());
                recepcionista.setEmail(usuarioDTO.email());
                recepcionista.setSenha(usuarioDTO.senha());
                recepcionista.setCPF(usuarioDTO.CPF());
                recepcionista.setRG(usuarioDTO.RG());
                recepcionista.setTelefone(usuarioDTO.telefone());
                usuario = recepcionista;
            }
            case PROFISSIONAL -> {
                Profissional profissional = new Profissional();
                profissional.setNome(usuarioDTO.nome());
                profissional.setEmail(usuarioDTO.email());
                profissional.setSenha(usuarioDTO.senha());
                profissional.setCPF(usuarioDTO.CPF());
                profissional.setRG(usuarioDTO.RG());
                profissional.setTelefone(usuarioDTO.telefone());
                profissional.setEspecialidade(usuarioDTO.especialidade());
                profissional.setRegistroProfissional(usuarioDTO.registroProfissional());
                usuario = profissional;
            }
        }
        return usuario;
    }
}
