package br.com.acolher.api.services;

import br.com.acolher.api.dtos.UsuarioCreateDTO;
import br.com.acolher.api.dtos.UsuarioResponseDTO;
import br.com.acolher.api.dtos.UsuarioUpdateDTO;
import br.com.acolher.api.entities.Usuario;
import br.com.acolher.api.mappers.UsuarioMapper;
import br.com.acolher.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO create(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioCreateDTO);
        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO read(Long id) {
        Usuario  usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário com id " + id +" não encontrado."));
        return UsuarioMapper.toDTO(usuario);
    }

    public List<UsuarioResponseDTO> readAll() {
        return usuarioRepository.findAll().stream().map(UsuarioMapper::toDTO).toList();
    }
    public UsuarioResponseDTO update(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário com id " + id + " não encontrado para atualização."));
        usuario = UsuarioMapper.updateEntity(usuarioUpdateDTO);
        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário com id + " + id + " não econtrado para deleção."));
        usuarioRepository.delete(usuario);
    }
}
