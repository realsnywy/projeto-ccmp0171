package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.UserCreateDTO;
import br.com.acolher.api.dtos.UserResponseDTO;
import br.com.acolher.api.dtos.UserUpdateDTO;
import br.com.acolher.api.entities.GeneralDirector;
import br.com.acolher.api.entities.Professional;
import br.com.acolher.api.entities.Recepcionist;
import br.com.acolher.api.entities.User;

public class UserMapper {
    public static User toEntity(UserCreateDTO userDTO) {
        User user = null;
        switch (userDTO.userType()) {
            case GENERAL_DIRECTOR -> {
                GeneralDirector generalDirector = new GeneralDirector();
                generalDirector.setName(userDTO.name());
                generalDirector.setEmail(userDTO.email());
                generalDirector.setPassword(userDTO.password());
                generalDirector.setCPF(userDTO.CPF());
                generalDirector.setRG(userDTO.RG());
                generalDirector.setTelephone(userDTO.telephone());
                user = generalDirector;
            }
            case RECEPCIONIST -> {
                Recepcionist recepcionist = new Recepcionist();
                recepcionist.setName(userDTO.name());
                recepcionist.setEmail(userDTO.email());
                recepcionist.setPassword(userDTO.password());
                recepcionist.setCPF(userDTO.CPF());
                recepcionist.setRG(userDTO.RG());
                recepcionist.setTelephone(userDTO.telephone());
                user = recepcionist;
            }
            case PROFESSIONAL -> {
                Professional professional = new Professional();
                professional.setName(userDTO.name());
                professional.setEmail(userDTO.email());
                professional.setPassword(userDTO.password());
                professional.setCPF(userDTO.CPF());
                professional.setRG(userDTO.RG());
                professional.setTelephone(userDTO.telephone());
                professional.setSpeciality(userDTO.speciality());
                professional.setProfessionalRegister(userDTO.professionalRegister());
                user = professional;
            }
            default -> throw new IllegalArgumentException("Tipo de usuário inválido: " + userDTO.userType());
        }
        return user;
    }

    public static UserResponseDTO toDTO(User user) {
        if(user instanceof Professional) {
            Professional professional = (Professional) user;
            return new UserResponseDTO(professional.getId(), professional.getName(), professional.getEmail(), professional.getPassword(), professional.getCPF(), professional.getRG(), professional.getTelephone(), professional.getSpeciality(), professional.getProfessionalRegister(), professional.getUserType());
        }else
            return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getCPF(), user.getRG(), user.getTelephone(), null, null, user.getUserType());
    }

    public static User updateEntity(UserUpdateDTO userDTO) {
        User user = null;
        switch (userDTO.userType()) {
            case GENERAL_DIRECTOR -> {
                GeneralDirector generalDirector = new GeneralDirector();
                generalDirector.setName(userDTO.name());
                generalDirector.setEmail(userDTO.email());
                generalDirector.setPassword(userDTO.password());
                generalDirector.setCPF(userDTO.CPF());
                generalDirector.setRG(userDTO.RG());
                generalDirector.setTelephone(userDTO.telephone());
                user = generalDirector;
            }
            case RECEPCIONIST -> {
                Recepcionist recepcionist = new Recepcionist();
                recepcionist.setName(userDTO.name());
                recepcionist.setEmail(userDTO.email());
                recepcionist.setPassword(userDTO.password());
                recepcionist.setCPF(userDTO.CPF());
                recepcionist.setRG(userDTO.RG());
                recepcionist.setTelephone(userDTO.telephone());
                user = recepcionist;
            }
            case PROFESSIONAL -> {
                Professional professional = new Professional();
                professional.setName(userDTO.name());
                professional.setEmail(userDTO.email());
                professional.setPassword(userDTO.password());
                professional.setCPF(userDTO.CPF());
                professional.setRG(userDTO.RG());
                professional.setTelephone(userDTO.telephone());
                professional.setSpeciality(userDTO.speciality());
                professional.setProfessionalRegister(userDTO.professionalRegister());
                user = professional;
            }
        }
        return user;
    }
}
