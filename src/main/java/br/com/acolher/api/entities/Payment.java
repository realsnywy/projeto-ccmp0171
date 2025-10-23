package br.com.acolher.api.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.acolher.api.enums.PaymentMethod;
import br.com.acolher.api.enums.PaymentStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamento")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pagamento", nullable = false)
    private PaymentMethod method;

    @Column(name = "data_pagamento")
    private LocalDateTime paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id")
    private Appointment appointment;

    @OneToMany(mappedBy = "pagamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentHistory> paymentHistory = new ArrayList<>();

    public Payment() {
    }

    public Payment(BigDecimal amount, PaymentStatus status, PaymentMethod method, LocalDateTime paymentDate,
                   Appointment appointment) {
        this.amount = amount;
        this.status = status;
        this.method = method;
        this.paymentDate = paymentDate;
        this.appointment = appointment;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setHistoricoPagamento(List<PaymentHistory> paymentHistory) {
        this.paymentHistory.clear();
        if (paymentHistory != null) {
            paymentHistory.forEach(this::addHistorico);
        }
    }

    public void addHistorico(PaymentHistory historico) {
        paymentHistory.add(historico);
        historico.setPagamento(this);
    }

    public void removeHistorico(PaymentHistory historico) {
        paymentHistory.remove(historico);
        historico.setPagamento(null);
    }

    /**
     * Returns the payment history entries filtered by pacienteId.
     */
    public List<PaymentHistory> paymentHistory(Long pacienteId) {
        if (paymentHistory == null) {
            return List.of();
        }
        return paymentHistory.stream()
                .filter(h -> {
                    try {
                        if (h == null) return false;
                        var paciente = h.getPaciente();
                        return Objects.equals(paciente != null ? paciente.getId() : null, pacienteId);
                    } catch (Exception ex) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", status=" + status +
                ", method=" + method +
                ", paymentDate=" + paymentDate +
                '}';
    }
}