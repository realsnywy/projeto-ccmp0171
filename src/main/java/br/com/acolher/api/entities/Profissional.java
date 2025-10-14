package br.com.acolher.api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "PROFISSIONAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profissional extends Usuario {
    private String especialidade;
    private String registroProfissional;
}
