package br.com.acolher.api.entities;

import br.com.acolher.api.config.CryptoUtil;
import br.com.acolher.api.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String cpf;

    private String rg;

    private String telephone;


    // Esses abaixos n são persistidos no banco. são usados para criptografar
    // e descriptografar durante requests
    @Transient
    private String rawCpf;
    @Transient
    private String rawRg;
    @Transient
    private String rawTelephone;


    @PrePersist
    public void prePersist() {
        this.cpf = CryptoUtil.encrypt(rawCpf);
        this.rg = CryptoUtil.encrypt(rawRg);
        this.telephone = CryptoUtil.encrypt(rawTelephone);
        prePersistChild();
    }

    // metodo criado só para pode criptografar campos de subclasses
    // já que n tem como sobrescrever o de cima
    protected void prePersistChild() {
    }

    @PostLoad
    public void postLoad() {
        this.rawCpf = CryptoUtil.decrypt(cpf);
        this.rawRg = CryptoUtil.decrypt(rg);
        this.rawTelephone = CryptoUtil.decrypt(telephone);
        postLoadChild();
    }

    protected void postLoadChild() {
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + getUserType().name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
