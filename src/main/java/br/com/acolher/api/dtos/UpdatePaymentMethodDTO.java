package br.com.acolher.api.dtos;

import br.com.acolher.api.enums.PaymentMethod;

public record UpdatePaymentMethodDTO(PaymentMethod paymentMethod) {
}
