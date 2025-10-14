package br.com.acolher.api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "DIRETOR_GERAL")
@Getter
@Setter
public class DiretorGeral extends Usuario {
}
