package br.com.acolher.api.services;

import br.com.acolher.api.dtos.AppointmentCreateDTO;
import br.com.acolher.api.dtos.AppointmentResponseDTO;
import br.com.acolher.api.dtos.AppointmentUpdateDTO;
import br.com.acolher.api.dtos.PatientResponseDTO;
import br.com.acolher.api.entities.Appointment;
import br.com.acolher.api.entities.Patient;
import br.com.acolher.api.entities.Payment;
import br.com.acolher.api.entities.Professional;
import br.com.acolher.api.enums.PaymentStatus;
import br.com.acolher.api.mappers.AppointmentMapper;
import br.com.acolher.api.mappers.PatientMapper;
import br.com.acolher.api.mappers.PaymentMapper;
import br.com.acolher.api.repositories.AppointmentRepository;
import br.com.acolher.api.repositories.PatientRepository;
import br.com.acolher.api.repositories.PaymentRepository;
import br.com.acolher.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    public AppointmentResponseDTO create(AppointmentCreateDTO appointmentCreateDTO) {
        Patient patient = null;
        if(appointmentCreateDTO.patientId() != null) {
            patient = patientRepository.findById(appointmentCreateDTO.patientId())
                    .orElseThrow(()-> new RuntimeException("Paciente com id " + appointmentCreateDTO.patientId() + " não encontrado"));
        }
        Professional professional = null;
        if(appointmentCreateDTO.professionalId() != null) {
            professional = (Professional) userRepository.findById(appointmentCreateDTO.professionalId())
                    .orElseThrow(()-> new RuntimeException("Profisional com id " + appointmentCreateDTO.patientId() + " não encontrado"));
        }
        Appointment appointment = AppointmentMapper.toEntity(appointmentCreateDTO, patient, professional);
        appointment = appointmentRepository.save(appointment);

        Payment payment = new Payment();
        payment.setAppointment(appointment);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setMethod(null);
        payment.setPaymentDate(null);
        payment.setAmount(appointment.getAmount());
        paymentRepository.save(payment);
        return AppointmentMapper.toDTO(appointment);
    }

    public AppointmentResponseDTO read(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Consulta com id " +id + " não encontrada"));
        return AppointmentMapper.toDTO(appointment);
    }

    public List<AppointmentResponseDTO> readAll() {
        return appointmentRepository.findAll().stream().map(AppointmentMapper::toDTO).toList();
    }

    public AppointmentResponseDTO update(AppointmentUpdateDTO appointmentUpdateDTO) {
        Appointment appointment = appointmentRepository.findById(appointmentUpdateDTO.id())
                .orElseThrow(()-> new RuntimeException("Consulta com id " + appointmentUpdateDTO.id() + " não encontrada para atualização"));
        Professional professional = null;
        if(appointmentUpdateDTO.professionalId() != null) {
            professional = (Professional) userRepository.findById(appointmentUpdateDTO.professionalId())
                    .orElseThrow(()-> new RuntimeException("Profissional com id " + appointmentUpdateDTO.professionalId() + " não encontrado para atualização da consulta"));
        }
        Patient patient = null;
        if(appointmentUpdateDTO.patientId() != null) {
            patient = patientRepository.findById(appointmentUpdateDTO.patientId())
                    .orElseThrow(()-> new RuntimeException("Paciente com id " + appointmentUpdateDTO.patientId() + " não encontrado para atualização da consulta"));
        }
        appointment.setPatient(patient);
        appointment.setProfessional(professional);
        appointment.setIssueDate(LocalDateTime.now());
        appointment.setAppointmentDate(appointmentUpdateDTO.appointmentDate());
        appointment.setAmount(appointmentUpdateDTO.amount());
        return AppointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    public void delete(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Consulta com id " + id + " não encontrada para deleção"));
        appointmentRepository.delete(appointment);
    }

    // vai buscar e retornar todos os pacientes de um profissional
    public List<PatientResponseDTO> findPatientsByProfessionalId(Long professionalId) {
        return appointmentRepository.findDistinctPatientsByProfessionalId(professionalId).stream().map(PatientMapper::toDTO).toList();
    }

    public List<AppointmentResponseDTO> findAllAppointmentsByProfessionalIdAndPatientId(Long professionalId, Long patientId) {
        return appointmentRepository.findByProfessionalIdAndPatientId(professionalId, patientId).stream().map(AppointmentMapper::toDTO).toList();
    }
}
