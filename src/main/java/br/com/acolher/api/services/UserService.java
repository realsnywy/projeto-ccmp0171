package br.com.acolher.api.services;

import br.com.acolher.api.dtos.UserCreateDTO;
import br.com.acolher.api.dtos.UserResponseDTO;
import br.com.acolher.api.dtos.UserUpdateDTO;
import br.com.acolher.api.entities.Professional;
import br.com.acolher.api.entities.User;
import br.com.acolher.api.enums.UserType;
import br.com.acolher.api.mappers.UserMapper;
import br.com.acolher.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserResponseDTO create(UserCreateDTO userCreateDTO) {
        // vai fazer a criptografia da senha e dps setar ela
        String encodedPassword = encoder.encode(userCreateDTO.password());
        User user = UserMapper.toEntity(userCreateDTO);
        user.setPassword(encodedPassword);
        return UserMapper.toDTO(usuarioRepository.save(user));
    }

    public UserResponseDTO read(Long id) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário com id " + id +" não encontrado."));
        return UserMapper.toDTO(user);
    }

    public List<UserResponseDTO> readAll() {
        return usuarioRepository.findAll().stream().map(UserMapper::toDTO).toList();
    }
    public UserResponseDTO update(UserUpdateDTO userUpdateDTO) {
        User user = usuarioRepository.findById(userUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Usuário com id " + userUpdateDTO.id() + " não encontrado para atualização."));
        user.setName(userUpdateDTO.name());
        user.setEmail(userUpdateDTO.email());
        String encodedPassword = encoder.encode(userUpdateDTO.password());
        user.setPassword(encodedPassword);
        user.setRawCpf(userUpdateDTO.cpf());
        user.setRawRg(userUpdateDTO.rg());
        user.setRawTelephone(userUpdateDTO.telephone());

        if (user instanceof Professional professional) {
            professional.setSpeciality(userUpdateDTO.speciality());
            professional.setRawProfessionalRegister(userUpdateDTO.professionalRegister());
        }

        return UserMapper.toDTO(usuarioRepository.save(user));
    }

    public void delete(Long id) {
        User user = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário com id + " + id + " não econtrado para deleção."));
        usuarioRepository.delete(user);
    }

    public List<UserResponseDTO> getAllProfessionals(){
        return usuarioRepository.findAllProfessionals().stream().map(UserMapper::toDTO).toList();
    }
}
