package br.com.acolher.api.repositories;

import br.com.acolher.api.entities.Appointment;
import br.com.acolher.api.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT DISTINCT a.patient FROM Appointment a WHERE a.professional.id = :professionalId")
    List<Patient> findDistinctPatientsByProfessionalId(@Param("professionalId") Long professionalId);
}
