package br.com.acolher.api.mappers;

import br.com.acolher.api.dtos.PaymentResponseDTO;
import br.com.acolher.api.entities.Payment;

public class PaymentMapper {
    public static PaymentResponseDTO toDTO(Payment payment) {
        return new PaymentResponseDTO(
                payment.getId(),
                payment.getAppointment().getId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getMethod(),
                payment.getPaymentDate()
        );
    }
}
