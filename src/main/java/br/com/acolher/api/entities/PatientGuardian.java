package br.com.acolher.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient_guardians")
public class PatientGuardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String CPF;
    private String RG;
    private String relationship;
    private String telephone;
    private String email;

    @OneToMany(mappedBy = "guardian")
    List<Patient> patients;
}