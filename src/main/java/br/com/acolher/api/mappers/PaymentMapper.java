package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.PaymentCreateDTO;
import br.com.acolher.api.dtos.PaymentResponseDTO;
import br.com.acolher.api.entities.Appointment;
import br.com.acolher.api.entities.Payment;

public class PaymentMapper {

    public static Payment toEntity(PaymentCreateDTO dto, Appointment appointment) {
        Payment payment = new Payment();
        payment.setAppointment(appointment);
        payment.setStatus(dto.status());
        payment.setMethod(dto.method());
        payment.setPaymentDate(dto.paymentDate());
        return payment;
    }

    public static PaymentResponseDTO toDTO(Payment payment) {
        return new PaymentResponseDTO(
                payment.getId(),
                payment.getAppointment().getId(),
                payment.getStatus(),
                payment.getMethod(),
                payment.getPaymentDate()
        );
    }
}
