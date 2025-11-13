package br.com.acolher.api.repositories;

import br.com.acolher.api.entities.MonthlySettlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthlySettlementRepository extends JpaRepository<MonthlySettlement, Long> {
    List<MonthlySettlement> findByProfessionalIdAndYearAndMonth(Long professionalId, int year, int month);
}
