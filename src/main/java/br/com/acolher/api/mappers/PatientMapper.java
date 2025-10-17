package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.PatientCreateDTO;
import br.com.acolher.api.dtos.PatientResponseDTO;
import br.com.acolher.api.entities.Patient;
import br.com.acolher.api.entities.PatientGuardian;
import br.com.acolher.api.enums.PatientStatus;

public class PatientMapper {

    public static Patient toEntity(PatientCreateDTO dto, PatientGuardian guardian) {
        Patient patient = new Patient();
        patient.setName(dto.name());
        patient.setSex(dto.sex());
        patient.setBirthDate(dto.birthDate());
        patient.setRg(dto.rg());
        patient.setCpf(dto.cpf());
        patient.setEmail(dto.email());
        patient.setTelephone(dto.telephone());
        patient.setStatus(PatientStatus.ACTIVE);
        patient.setDependent(dto.isDependent());
        patient.setGuardian(guardian);
        return patient;
    }

    public static PatientResponseDTO toDTO(Patient patient) {
        Long guardianId = null;
        if (patient.getGuardian() != null) {
            guardianId = patient.getGuardian().getId();
        }
        return new PatientResponseDTO(patient.getId(), patient.getName(), patient.getSex(), patient.getBirthDate(), patient.getCpf(), patient.getRg(), patient.getTelephone(), patient.getEmail(), patient.getStatus(), patient.isDependent(), guardianId);
    }
}
