package br.com.acolher.api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@DiscriminatorValue(value = "RECEPCIONISTA")
@Getter
@Setter
public class Recepcionista extends Usuario {
}
