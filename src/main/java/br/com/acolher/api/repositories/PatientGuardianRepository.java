package br.com.acolher.api.repositories;

import br.com.acolher.api.entities.PatientGuardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientGuardianRepository extends JpaRepository<PatientGuardian, Long> {
}
