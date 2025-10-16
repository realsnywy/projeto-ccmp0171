package br.com.acolher.api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Entity
@DiscriminatorValue(value = "RECEPCIONIST")
@Getter
@Setter
public class Recepcionist extends User {
}
