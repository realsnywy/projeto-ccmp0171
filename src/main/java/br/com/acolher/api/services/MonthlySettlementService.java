package br.com.acolher.api.services;

import br.com.acolher.api.dtos.MonthlySettlementResponseDTO;
import br.com.acolher.api.dtos.MonthlySettlementUpdateDTO;
import br.com.acolher.api.entities.MonthlySettlement;
import br.com.acolher.api.entities.Professional;
import br.com.acolher.api.entities.User;
import br.com.acolher.api.enums.SettlementStatus;
import br.com.acolher.api.mappers.MonthlySettlementMapper;
import br.com.acolher.api.repositories.MonthlySettlementRepository;
import br.com.acolher.api.repositories.PaymentRepository;
import br.com.acolher.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class MonthlySettlementService {

    @Autowired
    private MonthlySettlementRepository settlementRepository;
    @Autowired
    private UserRepository professionalRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    private static final BigDecimal SETTLEMENT_RATE = BigDecimal.valueOf(0.3);

    public void calculateAndSavePreviousMonthSettlement() {
        LocalDate today = LocalDate.now();
        LocalDate reference = today.minusMonths(1);
        int year = reference.getYear();
        int month = reference.getMonthValue();

        List<User> professionals = professionalRepository.findAll();
        for (User professional : professionals) {
            BigDecimal totalReceived = paymentRepository.sumPaymentsByProfessionalAndYearAndMonth(
                    professional.getId(), year, month
            );
            if (totalReceived == null) totalReceived = BigDecimal.ZERO;

            BigDecimal settlementAmount = totalReceived.multiply(SETTLEMENT_RATE);

            MonthlySettlement settlement = new MonthlySettlement();
            settlement.setProfessional(professional);
            settlement.setYear(year);
            settlement.setMonth(month);
            settlement.setTotalReceived(totalReceived);
            settlement.setSettlementAmount(settlementAmount);
            settlement.setStatus(SettlementStatus.PENDING);

            settlementRepository.save(settlement);
        }
    }

    public List<MonthlySettlementResponseDTO> readAll() {
        return settlementRepository.findAll().stream().map(MonthlySettlementMapper::toDTO).toList();
    }

    public MonthlySettlementResponseDTO readById(Long id) {
        MonthlySettlement settlement = settlementRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("MonthlySettlement not found"));
        return  MonthlySettlementMapper.toDTO(settlement);
    }


    public MonthlySettlementResponseDTO pay(MonthlySettlementUpdateDTO dto) {
        MonthlySettlement settlement = settlementRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("MonthlySettlement not found"));

        settlement.setTotalReceived(settlement.getTotalReceived().add(dto.totalReceived()));
        if (dto.totalReceived().compareTo(settlement.getSettlementAmount()) >= 0) {
            settlement.setStatus(SettlementStatus.PAID);
        } else {
            settlement.setStatus(SettlementStatus.PENDING);
        }

        settlement.setPaymentDate(dto.paymentDate());
        MonthlySettlement updated = settlementRepository.save(settlement);
        return MonthlySettlementMapper.toDTO(updated);
    }

}
