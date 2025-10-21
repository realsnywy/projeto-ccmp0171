package br.com.acolher.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "patients_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appointment_id", unique = true)
    private Appointment appointment;

    private String appointmentSummary;

    private LocalDateTime recordDate;

    @Lob
    private byte[] file;

    private String fileType;
}
