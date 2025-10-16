package br.com.acolher.api.entities;

import br.com.acolher.api.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
   name = "user_type",
   discriminatorType = DiscriminatorType.STRING
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String CPF;

    private String RG;

    private String telephone;

    public UserType getUserType() {
        if(this instanceof GeneralDirector) {
            return UserType.GENERAL_DIRECTOR;
        }
        if(this instanceof Recepcionist) {
            return UserType.RECEPCIONIST;
        }
        if(this instanceof Professional) {
            return UserType.PROFESSIONAL;
        }
        throw new IllegalStateException("Tipo de usuário desconhecido");
    }
}
