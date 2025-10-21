package br.com.acolher.api.repositories;

import br.com.acolher.api.entities.PatientRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecord, Long> {
}
