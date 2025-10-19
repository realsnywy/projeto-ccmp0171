package br.com.acolher.api.entities;

import br.com.acolher.api.config.CryptoUtil;
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
    private String cpf;
    private String rg;
    private String relationship;
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

    @OneToMany(mappedBy = "guardian")
    List<Patient> patients;

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