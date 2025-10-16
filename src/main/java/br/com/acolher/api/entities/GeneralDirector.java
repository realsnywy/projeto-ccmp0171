package br.com.acolher.api.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "GENERAL_DIRECTOR")
@Getter
@Setter
public class GeneralDirector extends User {
}
