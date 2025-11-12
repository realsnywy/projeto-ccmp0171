package br.com.acolher.api.repositories;

import br.com.acolher.api.entities.User;
import br.com.acolher.api.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findUserByEmail(String username);

    Optional<Object> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE TYPE(u) = Professional")
    List<User> findAllProfessionals();
}
