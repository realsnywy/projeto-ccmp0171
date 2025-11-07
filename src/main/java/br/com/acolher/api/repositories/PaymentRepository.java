package br.com.acolher.api.repositories;

import br.com.acolher.api.entities.Payment;
import br.com.acolher.api.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatusAndPaymentDateBefore(PaymentStatus paymentStatus, LocalDateTime oneMonthAgo);
}
