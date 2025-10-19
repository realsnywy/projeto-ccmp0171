package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.UserCreateDTO;
import br.com.acolher.api.dtos.UserResponseDTO;
import br.com.acolher.api.entities.GeneralDirector;
import br.com.acolher.api.entities.Professional;
import br.com.acolher.api.entities.Recepcionist;
import br.com.acolher.api.entities.User;


public class UserMapper {

    private static User setCommonFields(User user, UserCreateDTO userCreateDTO) {
        user.setName(userCreateDTO.name());
        user.setEmail(userCreateDTO.email());
        user.setPassword(userCreateDTO.password());
        user.setRawCpf(userCreateDTO.cpf());
        user.setRawRg(userCreateDTO.rg());
        user.setRawTelephone(userCreateDTO.telephone());
        return user;
    }

    public static User toEntity(UserCreateDTO userDTO) {
        User user;
        switch (userDTO.userType()) {
            case GENERAL_DIRECTOR -> user = setCommonFields(new GeneralDirector(), userDTO);
            case RECEPCIONIST -> user = setCommonFields(new Recepcionist(), userDTO);
            case PROFESSIONAL -> {
                Professional professional = (Professional) setCommonFields(new Professional(), userDTO);
                professional.setSpeciality(userDTO.speciality());
                professional.setRawProfessionalRegister(userDTO.professionalRegister());
                user = professional;
            }
            default -> throw new IllegalArgumentException("Tipo de usuário inválido: " + userDTO.userType());
        }
        return user;
    }

    public static UserResponseDTO toDTO(User user) {
        if(user instanceof Professional professional) {
            return new UserResponseDTO(professional.getId(), professional.getName(), professional.getEmail(), professional.getPassword(), professional.getRawCpf(), professional.getRawRg(), professional.getRawTelephone(), professional.getSpeciality(), professional.getRawProfessionalRegister(), professional.getUserType());
        }else
            return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRawCpf(), user.getRawRg(), user.getRawTelephone(), null, null, user.getUserType());
    }
}
