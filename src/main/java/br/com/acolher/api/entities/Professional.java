package br.com.acolher.api.entities;

import br.com.acolher.api.config.CryptoUtil;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "PROFESSIONAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professional extends User {
    private String speciality;
    private String professionalRegister;

    @Transient
    private String rawProfessionalRegister;

    @Override
    protected void prePersistChild() {
        this.professionalRegister = CryptoUtil.encrypt(rawProfessionalRegister);
    }

    @Override
    protected void postLoadChild() {
        this.rawProfessionalRegister = CryptoUtil.decrypt(professionalRegister);
    }

}
