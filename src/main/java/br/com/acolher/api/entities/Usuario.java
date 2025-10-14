package br.com.acolher.api.entities;

import br.com.acolher.api.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
   name = "tipo_usuario",
   discriminatorType = DiscriminatorType.STRING
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String CPF;

    private String RG;

    private String telefone;

    public TipoUsuario getTipoUsuario() {
        if(this instanceof DiretorGeral) {
            return TipoUsuario.DIRETOR_GERAL;
        }
        if(this instanceof Recepcionista) {
            return TipoUsuario.RECEPCIONISTA;
        }
        if(this instanceof Profissional) {
            return TipoUsuario.PROFISSIONAL;
        }
        throw new IllegalStateException("Tipo de usuário desconhecido");
    }
}
