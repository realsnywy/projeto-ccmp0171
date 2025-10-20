package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.AppointmentCreateDTO;
import br.com.acolher.api.dtos.AppointmentResponseDTO;
import br.com.acolher.api.entities.Appointment;
import br.com.acolher.api.entities.Patient;
import br.com.acolher.api.entities.Professional;
import br.com.acolher.api.entities.User;

import java.time.LocalDateTime;

public class AppointmentMapper {

    public static Appointment toEntity(AppointmentCreateDTO appointmentCreateDTO, Patient patient, Professional professional) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setProfessional(professional);
        appointment.setIssueDate(LocalDateTime.now());
        appointment.setAppointmentDate(appointmentCreateDTO.appointmentDate());
        return appointment;
    }

    public static AppointmentResponseDTO toDTO(Appointment appointment) {
        return new AppointmentResponseDTO(appointment.getId(), appointment.getIssueDate(), appointment.getAppointmentDate(), appointment.getPatient().getId(), appointment.getProfessional().getId());
    }
}
