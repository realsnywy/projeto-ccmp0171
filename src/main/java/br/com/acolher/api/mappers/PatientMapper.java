package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.PatientCreateDTO;
import br.com.acolher.api.dtos.PatientResponseDTO;
import br.com.acolher.api.entities.Patient;
import br.com.acolher.api.entities.PatientGuardian;
import br.com.acolher.api.enums.PatientStatus;

public class PatientMapper {

    public static Patient toEntity(PatientCreateDTO dto, PatientGuardian guardian) {
        Patient patient = new Patient();
        patient.setRawName(dto.name());
        patient.setSex(dto.sex());
        patient.setBirthDate(dto.birthDate());
        patient.setRawRg(dto.rg());
        patient.setRawCpf(dto.cpf());
        patient.setRawEmail(dto.email());
        patient.setRawTelephone(dto.telephone());
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
        return new PatientResponseDTO(patient.getId(), patient.getRawName(), patient.getSex(), patient.getBirthDate(), patient.getRawCpf(), patient.getRawRg(), patient.getRawTelephone(), patient.getRawEmail(), patient.getStatus(), patient.isDependent(), guardianId);
    }
}
