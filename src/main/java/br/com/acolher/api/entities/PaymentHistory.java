package br.com.acolher.api.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "historico_pagamento")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment pagamento;

    @Column(name = "evento")
    private String evento;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public PaymentHistory() {
    }

    public PaymentHistory(Patient paciente, Payment pagamento, String evento) {
        this.paciente = paciente;
        this.pagamento = pagamento;
        this.evento = evento;
    }

    public Long getId() {
        return id;
    }

    public Patient getPaciente() {
        return paciente;
    }

    public void setPaciente(Patient paciente) {
        this.paciente = paciente;
    }

    public Payment getPagamento() {
        return pagamento;
    }

    public void setPagamento(Payment pagamento) {
        this.pagamento = pagamento;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}