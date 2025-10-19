package br.com.acolher.api.services;

import br.com.acolher.api.dtos.PatientCreateDTO;
import br.com.acolher.api.dtos.PatientResponseDTO;
import br.com.acolher.api.dtos.PatientUpdateDTO;
import br.com.acolher.api.entities.Patient;
import br.com.acolher.api.entities.PatientGuardian;
import br.com.acolher.api.enums.PatientStatus;
import br.com.acolher.api.mappers.PatientMapper;
import br.com.acolher.api.repositories.PatientGuardianRepository;
import br.com.acolher.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientGuardianRepository guardianRepository;

    public PatientResponseDTO create(PatientCreateDTO dto) {
        PatientGuardian guardian = null;
        if(dto.guardianId() != null)
           guardian = guardianRepository.findById(dto.guardianId()).orElseThrow(()->new RuntimeException("Responsável com id " +  dto.guardianId() + " não encontrado"));
        Patient patient = PatientMapper.toEntity(dto, guardian);
        return  PatientMapper.toDTO(patientRepository.save(patient));
    }

    public PatientResponseDTO read(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("Paciente com id " + id + " não encontrado"));
        return PatientMapper.toDTO(patient);
    }

    public List<PatientResponseDTO> readAll() {
        return patientRepository.findAll().stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO update(PatientUpdateDTO dto) {
        Patient patient = patientRepository.findById(dto.id()).orElseThrow(() -> new RuntimeException("Paciente com id " +  dto.id() + " não encontrado para alteração"));
        PatientGuardian guardian = null;
        if(dto.guardianId() != null)
            guardian = guardianRepository.findById(dto.guardianId()).orElseThrow(()->new RuntimeException("Responsável com id " +  dto.guardianId() + " não encontrado para atualizar o paciente"));
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
        return PatientMapper.toDTO(patientRepository.save(patient));
    }

    public void delete(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("Paciente com id " + id + " não encontrado para deleção"));
        patientRepository.delete(patient);
    }
}
