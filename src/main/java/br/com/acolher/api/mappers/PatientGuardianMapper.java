package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.PatientGuardianCreateDTO;
import br.com.acolher.api.dtos.PatientGuardianResponseDTO;
import br.com.acolher.api.dtos.PatientGuardianUpdateDTO;
import br.com.acolher.api.entities.PatientGuardian;

public class PatientGuardianMapper {
    public static PatientGuardian toEntity(PatientGuardianCreateDTO patientGuardianCreateDTO){
        PatientGuardian patientGuardian = new PatientGuardian();
        patientGuardian.setRawName(patientGuardianCreateDTO.name());
        patientGuardian.setRawCpf(patientGuardianCreateDTO.cpf());
        patientGuardian.setRawRg(patientGuardianCreateDTO.rg());
        patientGuardian.setRawTelephone(patientGuardianCreateDTO.telephone());
        patientGuardian.setRawEmail(patientGuardianCreateDTO.email());
        patientGuardian.setRelationship(patientGuardianCreateDTO.relationship());

        return patientGuardian;
    }

    public static PatientGuardianResponseDTO toDTO(PatientGuardian patientGuardian){
        return new PatientGuardianResponseDTO(patientGuardian.getId(), patientGuardian.getRawName(), patientGuardian.getRawCpf(), patientGuardian.getRawRg(), patientGuardian.getRelationship(), patientGuardian.getRawTelephone(), patientGuardian.getRawEmail());
    }

}
