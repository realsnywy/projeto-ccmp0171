package br.com.acolher.api.services;

import br.com.acolher.api.dtos.PatientRecordCreateDTO;
import br.com.acolher.api.dtos.PatientRecordResponseDTO;
import br.com.acolher.api.dtos.PatientRecordUpdateDTO;
import br.com.acolher.api.entities.Appointment;
import br.com.acolher.api.entities.PatientRecord;
import br.com.acolher.api.mappers.PatientRecordMapper;
import br.com.acolher.api.repositories.AppointmentRepository;
import br.com.acolher.api.repositories.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PatientRecordService {

    @Autowired
    private PatientRecordRepository patientRecordRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public PatientRecordResponseDTO create(PatientRecordCreateDTO patientRecordCreateDTO){
        Appointment appointment = appointmentRepository.findById(patientRecordCreateDTO.appointmentId())
                .orElseThrow(()->new RuntimeException("Consulta com id " + patientRecordCreateDTO.appointmentId() + " não encontrada"));
        //essa parte aqui é salvar o arquivo enviado
        byte[] file = null;
        String fileType = null;
        if (patientRecordCreateDTO.file() != null && !patientRecordCreateDTO.file().isEmpty()) {
            try {
                file = patientRecordCreateDTO.file().getBytes();
                fileType = patientRecordCreateDTO.file().getContentType();
            } catch (IOException e) {
                throw new RuntimeException("Erro ao processar arquivo", e);
            }
        }
        PatientRecord patientRecord = PatientRecordMapper.toEntity(patientRecordCreateDTO, appointment, file, fileType);
        return PatientRecordMapper.toDTO(patientRecordRepository.save(patientRecord));
    }

    public PatientRecordResponseDTO read(Long id) {
        PatientRecord patientRecord = patientRecordRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Prontuário com id " + id + " não encontrado"));
        return PatientRecordMapper.toDTO(patientRecord);
    }

    public List<PatientRecordResponseDTO> readAll() {
        return patientRecordRepository.findAll().stream().map(PatientRecordMapper::toDTO).toList();
    }

    public PatientRecordResponseDTO update(PatientRecordUpdateDTO patientRecordUpdateDTO) {
        PatientRecord patientRecord = patientRecordRepository.findById(patientRecordUpdateDTO.id())
                .orElseThrow(()-> new RuntimeException("Prontuário com id " + patientRecordUpdateDTO.id() + " não encontrado para alteração"));
        patientRecord.setAppointmentSummary(patientRecordUpdateDTO.appointmentSummary());
        patientRecord.setRecordDate(patientRecordUpdateDTO.recordDate());

        //mesma coisa do de cima só muda que faz a checagem se n veio nulo antes
        //para n acabar sobreescrevendo oq já tinha com nulo
        if (patientRecordUpdateDTO.file() != null && !patientRecordUpdateDTO.file().isEmpty()) {
            try {
                byte[] file =  patientRecordUpdateDTO.file().getBytes();
                patientRecord.setFile(file);
                patientRecord.setFileType(patientRecordUpdateDTO.file().getContentType());
            } catch (IOException e) {
                throw new RuntimeException("Erro ao processar arquivo", e);
            }
        }

        return  PatientRecordMapper.toDTO(patientRecordRepository.save(patientRecord));
    }

    public void delete(Long id) {
        PatientRecord patientRecord = patientRecordRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Prontuário com id " + id + " não encontrado para alteração"));
        patientRecordRepository.delete(patientRecord);
    }

    // esse método é responsavel por enviar o arquivo para o front
    public byte[] getFile(Long id) {
        PatientRecord pr = patientRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
        byte[] file = pr.getFile();
        if (file == null) {
            throw new RuntimeException("Arquivo não encontrado para o prontuário");
        }
        return file;
    }

    //metodo auxiliar só para mudar dinamicamente o tipo de arquivo q vai ser enviado
    public String getFileType(Long id) {
        PatientRecord pr = patientRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));
        return pr.getFileType();
    }

    // vai procurar se existe algum prontuário de uma consulta
    public PatientRecordResponseDTO findPatientRecordsByAppointmentId(Long appointmentId) {
        PatientRecord patientRecord = patientRecordRepository.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado para a consulta id: " + appointmentId));
        return PatientRecordMapper.toDTO(patientRecord);
    }
}
