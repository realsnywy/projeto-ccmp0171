package br.com.acolher.api.config;

import br.com.acolher.api.entities.GeneralDirector;
import br.com.acolher.api.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admin_email}")
    private String adminEmail;

    @Value("${admin_password}")
    private String adminPassword;
    @PostConstruct
    public void init() {


        boolean exists = userRepository.findByEmail(adminEmail).isPresent();

        if (!exists) {
            GeneralDirector admin = new GeneralDirector();
            admin.setName("Administrador");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRawCpf("00000000000");
            admin.setRawRg("0000000");
            admin.setRawTelephone("000000000");

            userRepository.save(admin);
            System.out.println("Usuário admin (GeneralDirector) criado com sucesso.");
        } else {
            System.out.println("Usuário admin já existe.");
        }
    }
}
