package br.com.acolher.api.services;

import br.com.acolher.api.dtos.UserCreateDTO;
import br.com.acolher.api.dtos.UserResponseDTO;
import br.com.acolher.api.dtos.UserUpdateDTO;
import br.com.acolher.api.entities.User;
import br.com.acolher.api.mappers.UserMapper;
import br.com.acolher.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepository;

    public UserResponseDTO create(UserCreateDTO userCreateDTO) {
        User user = UserMapper.toEntity(userCreateDTO);
        return UserMapper.toDTO(usuarioRepository.save(user));
    }

    public UserResponseDTO read(Long id) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário com id " + id +" não encontrado."));
        return UserMapper.toDTO(user);
    }

    public List<UserResponseDTO> readAll() {
        return usuarioRepository.findAll().stream().map(UserMapper::toDTO).toList();
    }
    public UserResponseDTO update(Long id, UserUpdateDTO userUpdateDTO) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário com id " + id + " não encontrado para atualização."));
        user = UserMapper.updateEntity(userUpdateDTO);
        return UserMapper.toDTO(usuarioRepository.save(user));
    }

    public void delete(Long id) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário com id + " + id + " não econtrado para deleção."));
        usuarioRepository.delete(user);
    }
}
