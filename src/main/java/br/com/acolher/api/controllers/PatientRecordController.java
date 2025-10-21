package br.com.acolher.api.controllers;

import br.com.acolher.api.dtos.PatientRecordCreateDTO;
import br.com.acolher.api.dtos.PatientRecordResponseDTO;
import br.com.acolher.api.dtos.PatientRecordUpdateDTO;
import br.com.acolher.api.entities.PatientRecord;
import br.com.acolher.api.services.PatientRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients-records")
public class PatientRecordController {
    @Autowired
    private PatientRecordService service;

    @PreAuthorize("hasRole('PROFESSIONAL')")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<PatientRecordResponseDTO> create(@ModelAttribute PatientRecordCreateDTO patientRecordCreateDTO) {
        try{
            return new ResponseEntity<>(service.create(patientRecordCreateDTO), HttpStatus.CREATED);
        }catch (RuntimeException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // pega só os dados da consulta sem o arquivo
    @PreAuthorize("hasRole('PROFESSIONAL')")
    @GetMapping
    public ResponseEntity<List<PatientRecordResponseDTO>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    // pega só os dados da consultas sem os arquivos
    @PreAuthorize("hasRole('PROFESSIONAL')")
    @GetMapping("/{id}")
    public ResponseEntity<PatientRecordResponseDTO> read(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(service.read(id), HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // vai enviar o arquivo para o front
    @PreAuthorize("hasRole('PROFESSIONAL')")
    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        byte[] fileData = service.getFile(id);
        String contentType = service.getFileType(id);

        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
        if (contentType != null && !contentType.isBlank()) {
            mediaType = MediaType.parseMediaType(contentType);
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=\"prontuario_" + id + "\"")
                .contentType(mediaType)
                .body(fileData);
    }

    @PreAuthorize("hasRole('PROFESSIONAL')")
    @PutMapping(consumes = "multipart/form-data")
    public ResponseEntity<PatientRecordResponseDTO> update(@ModelAttribute PatientRecordUpdateDTO patientRecordUpdateDTO) {
        try{
            return new ResponseEntity<>(service.update(patientRecordUpdateDTO), HttpStatus.OK);
        } catch (RuntimeException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('PROFESSIONAL')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>("Prontuário deletado com sucesso!", HttpStatus.OK);
        } catch (RuntimeException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //vai retornar o prontuario de acordo com id da consulta
    @PreAuthorize("hasRole('PROFESSIONAL')")
    @GetMapping("/by-appointment/{appointmentId}")
    public ResponseEntity<PatientRecordResponseDTO> readByAppointmentId(@PathVariable Long appointmentId) {
        try{
            return new ResponseEntity<>(service.findPatientRecordsByAppointmentId(appointmentId), HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
