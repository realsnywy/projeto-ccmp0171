package br.com.acolher.api.controllers;

import br.com.acolher.api.dtos.AppointmentCreateDTO;
import br.com.acolher.api.dtos.AppointmentResponseDTO;
import br.com.acolher.api.dtos.AppointmentUpdateDTO;
import br.com.acolher.api.dtos.PatientResponseDTO;
import br.com.acolher.api.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('GENERAL_DIRECTOR', 'RECEPCIONIST')")
    public ResponseEntity<AppointmentResponseDTO> create(@RequestBody AppointmentCreateDTO appointmentDTO) {
        AppointmentResponseDTO response = appointmentService.create(appointmentDTO);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('GENERAL_DIRECTOR', 'RECEPCIONIST')")
    public ResponseEntity<List<AppointmentResponseDTO>> readAll() {
        List<AppointmentResponseDTO> response = appointmentService.readAll();
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> read(@PathVariable Long id) {
        try{
            AppointmentResponseDTO response = appointmentService.read(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('GENERAL_DIRECTOR', 'RECEPCIONIST')")
    public ResponseEntity<AppointmentResponseDTO> update(@RequestBody AppointmentUpdateDTO appointmentUpdateDTO) {
        try {
            AppointmentResponseDTO response = appointmentService.update(appointmentUpdateDTO);
            return new  ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('GENERAL_DIRECTOR', 'RECEPCIONIST')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try{
            appointmentService.delete(id);
            return new ResponseEntity<>("Consulta deletada com sucesso",HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('PROFESSIONAL')")
    @GetMapping("/patients/by-professional/{professionalId}")
    public ResponseEntity<List<PatientResponseDTO>> readPatientsByProfessionalId(@PathVariable Long professionalId) {
        return new ResponseEntity<>(appointmentService.findPatientsByProfessionalId(professionalId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PROFESSIONAL')")
    @GetMapping("/by/professional/{professionalId}/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> readPatientsByProfessionalIdAndPatientId(@PathVariable Long professionalId, @PathVariable Long patientId) {
        return new ResponseEntity<>(appointmentService.findAllAppointmentsByProfessionalIdAndPatientId(professionalId, patientId), HttpStatus.OK);
    }
}
