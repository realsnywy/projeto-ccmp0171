package br.com.acolher.api.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Calculates how a payment total is split between provider, clinic and a fixed fee.
 * - providerRate is expected as a fraction (e.g. 0.70 for 70\%).
 */
public class TransferCalculation {

    private final BigDecimal total;
    private final BigDecimal providerRate;
    private final BigDecimal fixedFee;

    private final BigDecimal providerAmount;
    private final BigDecimal clinicAmount;
    private final LocalDate calculationDate;
    private final Map<String, BigDecimal> breakdown;

    private TransferCalculation(BigDecimal total,
                             BigDecimal providerRate,
                             BigDecimal fixedFee,
                             BigDecimal providerAmount,
                             BigDecimal clinicAmount,
                             LocalDate calculationDate,
                             Map<String, BigDecimal> breakdown) {
        this.total = total;
        this.providerRate = providerRate;
        this.fixedFee = fixedFee;
        this.providerAmount = providerAmount;
        this.clinicAmount = clinicAmount;
        this.calculationDate = calculationDate;
        this.breakdown = breakdown;
    }

    public static TransferCalculation calculate(BigDecimal total, BigDecimal providerRate, BigDecimal fixedFee) {
        Objects.requireNonNull(total, "total must not be null");
        Objects.requireNonNull(providerRate, "providerRate must not be null");
        Objects.requireNonNull(fixedFee, "fixedFee must not be null");

        if (total.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("total must be non-negative");
        }
        if (providerRate.compareTo(BigDecimal.ZERO) < 0 || providerRate.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("providerRate must be between 0 and 1 (fraction)");
        }
        if (fixedFee.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("fixedFee must be non-negative");
        }

        // rounding to 2 decimal places (currency)
        BigDecimal providerAmount = total.multiply(providerRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal clinicAmount = total.subtract(providerAmount).subtract(fixedFee).setScale(2, RoundingMode.HALF_UP);

        // ensure clinicAmount is not negative after fees
        if (clinicAmount.compareTo(BigDecimal.ZERO) < 0) {
            clinicAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        Map<String, BigDecimal> breakdown = new LinkedHashMap<>();
        breakdown.put("total", total.setScale(2, RoundingMode.HALF_UP));
        breakdown.put("providerRate", providerRate.setScale(4, RoundingMode.HALF_UP));
        breakdown.put("fixedFee", fixedFee.setScale(2, RoundingMode.HALF_UP));
        breakdown.put("providerAmount", providerAmount);
        breakdown.put("clinicAmount", clinicAmount);

        return new TransferCalculation(
                total.setScale(2, RoundingMode.HALF_UP),
                providerRate.setScale(4, RoundingMode.HALF_UP),
                fixedFee.setScale(2, RoundingMode.HALF_UP),
                providerAmount,
                clinicAmount,
                LocalDate.now(),
                Collections.unmodifiableMap(breakdown)
        );
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getProviderRate() {
        return providerRate;
    }

    public BigDecimal getFixedFee() {
        return fixedFee;
    }

    public BigDecimal getProviderAmount() {
        return providerAmount;
    }

    public BigDecimal getClinicAmount() {
        return clinicAmount;
    }

    public LocalDate getCalculationDate() {
        return calculationDate;
    }

    /**
     * Read-only breakdown map: keys include total, providerRate, fixedFee, providerAmount, clinicAmount.
     */
    public Map<String, BigDecimal> getBreakdown() {
        return breakdown;
    }

    @Override
    public String toString() {
        return "CalculoDeRepasse{" +
                "total=" + total +
                ", providerRate=" + providerRate +
                ", fixedFee=" + fixedFee +
                ", providerAmount=" + providerAmount +
                ", clinicAmount=" + clinicAmount +
                ", calculationDate=" + calculationDate +
                '}';
    }
}
