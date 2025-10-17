package br.com.acolher.api.entities;

import br.com.acolher.api.enums.PatientStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private char sex;

    private LocalDate birthDate;

    private String cpf;

    private String rg;

    private String telephone;

    private String email;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;

    private boolean isDependent = false;

    @ManyToOne
    @JoinColumn(name = "guardian_id", nullable = true)
    private PatientGuardian guardian;
}
