package br.com.acolher.api.entities;

import br.com.acolher.api.config.CryptoUtil;
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

    @Transient
    private String rawName;
    @Transient
    private String rawCpf;
    @Transient
    private String rawRg;
    @Transient
    private String rawEmail;
    @Transient
    private String rawTelephone;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;

    private boolean isDependent = false;

    @ManyToOne
    @JoinColumn(name = "guardian_id", nullable = true)
    private PatientGuardian guardian;

    @PrePersist
    public void prePersist() {
        this.name = CryptoUtil.encrypt(rawName);
        this.cpf = CryptoUtil.encrypt(rawCpf);
        this.rg = CryptoUtil.encrypt(rawRg);
        this.telephone = CryptoUtil.encrypt(rawTelephone);
        this.email = CryptoUtil.encrypt(rawEmail);
    }

    @PostLoad
    public void postLoad() {
        this.rawName = CryptoUtil.decrypt(name);
        this.rawCpf = CryptoUtil.decrypt(cpf);
        this.rawRg = CryptoUtil.decrypt(rg);
        this.rawEmail = CryptoUtil.decrypt(email);
        this.rawTelephone = CryptoUtil.decrypt(telephone);
    }
}
