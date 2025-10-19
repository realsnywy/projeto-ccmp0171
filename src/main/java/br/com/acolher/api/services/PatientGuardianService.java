package br.com.acolher.api.services;

import br.com.acolher.api.dtos.PatientGuardianCreateDTO;
import br.com.acolher.api.dtos.PatientGuardianResponseDTO;
import br.com.acolher.api.dtos.PatientGuardianUpdateDTO;
import br.com.acolher.api.entities.Patient;
import br.com.acolher.api.entities.PatientGuardian;
import br.com.acolher.api.mappers.PatientGuardianMapper;
import br.com.acolher.api.repositories.PatientGuardianRepository;
import br.com.acolher.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientGuardianService {

    @Autowired
    private PatientGuardianRepository patientGuardianRepository;
    @Autowired
    private PatientRepository patientRepository;

    public PatientGuardianResponseDTO create(PatientGuardianCreateDTO patientGuardianCreateDTO) {
        PatientGuardian patientGuardian = PatientGuardianMapper.toEntity(patientGuardianCreateDTO);
        return PatientGuardianMapper.toDTO(patientGuardianRepository.save(patientGuardian));
    }

    public PatientGuardianResponseDTO read(Long id) {
        PatientGuardian patientGuardian = patientGuardianRepository.findById(id).orElseThrow(()-> new RuntimeException("Responsável com id " + id + " não encontrado"));
        return PatientGuardianMapper.toDTO(patientGuardian);
    }

    public List<PatientGuardianResponseDTO> readAll() {
        return patientGuardianRepository.findAll().stream().map(PatientGuardianMapper::toDTO).toList();
    }

    public PatientGuardianResponseDTO update(PatientGuardianUpdateDTO patientGuardianUpdateDTO) {
        PatientGuardian patientGuardian = patientGuardianRepository.findById(patientGuardianUpdateDTO.id()).orElseThrow(()->new RuntimeException("Responsável com id " + patientGuardianUpdateDTO.id() + " não encontrado para atualização"));
        patientGuardian.setRawName(patientGuardianUpdateDTO.name());
        patientGuardian.setRawCpf(patientGuardianUpdateDTO.cpf());
        patientGuardian.setRawRg(patientGuardianUpdateDTO.rg());
        patientGuardian.setRelationship(patientGuardianUpdateDTO.relationship());
        patientGuardian.setRawTelephone(patientGuardianUpdateDTO.telephone());
        patientGuardian.setRawEmail(patientGuardianUpdateDTO.email());
        return  PatientGuardianMapper.toDTO(patientGuardianRepository.save(patientGuardian));
    }

    public void delete(Long id) {
        PatientGuardian patientGuardian = patientGuardianRepository.findById(id).orElseThrow(()->new RuntimeException("Responsável com id " + id + " não encotrado para deleção"));
        // antes de apagar o responsável do banco, ele é desvinculado dos pacientes que ele era responsável
        for(Patient patient : patientGuardian.getPatients()) {
            patient.setGuardian(null);
            patientRepository.save(patient);
        }
        patientGuardianRepository.delete(patientGuardian);
    }
}
