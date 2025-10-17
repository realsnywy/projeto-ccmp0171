package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.PatientGuardianCreateDTO;
import br.com.acolher.api.dtos.PatientGuardianResponseDTO;
import br.com.acolher.api.dtos.PatientGuardianUpdateDTO;
import br.com.acolher.api.entities.PatientGuardian;

public class PatientGuardianMapper {
    public static PatientGuardian toEntity(PatientGuardianCreateDTO patientGuardianCreateDTO){
        PatientGuardian patientGuardian = new PatientGuardian();
        patientGuardian.setName(patientGuardianCreateDTO.name());
        patientGuardian.setCPF(patientGuardianCreateDTO.cpf());
        patientGuardian.setRG(patientGuardianCreateDTO.rg());
        patientGuardian.setTelephone(patientGuardianCreateDTO.telephone());
        patientGuardian.setEmail(patientGuardianCreateDTO.email());

        return patientGuardian;
    }

    public static PatientGuardianResponseDTO toDTO(PatientGuardian patientGuardian){
        return new PatientGuardianResponseDTO(patientGuardian.getId(), patientGuardian.getName(), patientGuardian.getCPF(), patientGuardian.getRG(), patientGuardian.getRelationship(), patientGuardian.getTelephone(), patientGuardian.getEmail());
    }

}
