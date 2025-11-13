package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.MonthlySettlementResponseDTO;
import br.com.acolher.api.entities.MonthlySettlement;

public class MonthlySettlementMapper {
    public static MonthlySettlementResponseDTO toDTO(MonthlySettlement settlement) {
        return new MonthlySettlementResponseDTO(settlement.getId(), settlement.getProfessional().getId(), settlement.getYear(), settlement.getMonth(), settlement.getTotalReceived(), settlement.getSettlementAmount(), settlement.getStatus(), settlement.getPaymentDate());
    }
}
