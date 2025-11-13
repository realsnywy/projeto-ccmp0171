package br.com.acolher.api.scheduling;

import br.com.acolher.api.services.MonthlySettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonthlySettlementScheduler {

    @Autowired
    private MonthlySettlementService settlementService;

    // Runs at 2 AM on the 1st of every month
    @Scheduled(cron = "0 0 2 1 * ?")
    public void runMonthlySettlement() {
        settlementService.calculateAndSavePreviousMonthSettlement();
    }
}
