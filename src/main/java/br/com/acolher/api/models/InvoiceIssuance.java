package br.com.acolher.api.models;

import br.com.acolher.api.entities.Patient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Simple invoice/receipt object created from a patient and a repass calculation.
 */
public class InvoiceIssuance {

    private final String invoiceNumber;
    private final LocalDateTime issuedAt;
    private final BigDecimal amount;
    private final String recipientName;
    private final String recipientDocument; // CPF/CNPJ (raw)
    private final TransferCalculation calculoDeRepasse;
    private final boolean issued;

    private InvoiceIssuance(String invoiceNumber,
                                LocalDateTime issuedAt,
                                BigDecimal amount,
                                String recipientName,
                                String recipientDocument,
                                TransferCalculation calculoDeRepasse,
                                boolean issued) {
        this.invoiceNumber = invoiceNumber;
        this.issuedAt = issuedAt;
        this.amount = amount;
        this.recipientName = recipientName;
        this.recipientDocument = recipientDocument;
        this.calculoDeRepasse = calculoDeRepasse;
        this.issued = issued;
    }

    /**
     * Builds and marks the invoice as issued. Uses patient raw fields (Patient.getRawName/getRawCpf).
     */
    public static InvoiceIssuance emitir(Patient patient, TransferCalculation calculo) {
        Objects.requireNonNull(patient, "patient must not be null");
        Objects.requireNonNull(calculo, "calculoDeRepasse must not be null");

        String invoiceNumber = generateInvoiceNumber();
        LocalDateTime now = LocalDateTime.now();
        BigDecimal amount = calculo.getTotal();
        String recipientName = patient.getRawName() != null ? patient.getRawName() : patient.getName();
        String recipientDocument = patient.getRawCpf() != null ? patient.getRawCpf() : patient.getCpf();

        return new InvoiceIssuance(
                invoiceNumber,
                now,
                amount,
                recipientName,
                recipientDocument,
                calculo,
                true
        );
    }

    private static String generateInvoiceNumber() {
        // Example simple invoice id: NF-<UUID-short>
        String shortId = UUID.randomUUID().toString().split("-")[0].toUpperCase();
        return "NF-" + shortId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientDocument() {
        return recipientDocument;
    }

    public TransferCalculation getCalculoDeRepasse() {
        return calculoDeRepasse;
    }

    public boolean isIssued() {
        return issued;
    }

    @Override
    public String toString() {
        return "EmissaoDeNotaFiscal{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", issuedAt=" + issuedAt +
                ", amount=" + amount +
                ", recipientName='" + recipientName + '\'' +
                ", recipientDocument='" + recipientDocument + '\'' +
                ", issued=" + issued +
                '}';
    }
}
