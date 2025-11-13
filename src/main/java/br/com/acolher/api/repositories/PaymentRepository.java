package br.com.acolher.api.repositories;

import br.com.acolher.api.entities.Payment;
import br.com.acolher.api.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStatusAndAppointment_AppointmentDateBefore(PaymentStatus status, LocalDateTime date);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.appointment.professional.id = :professionalId AND YEAR(p.paymentDate) = :year AND MONTH(p.paymentDate) = :month AND p.status = br.com.acolher.api.enums.PaymentStatus.PAID")
    BigDecimal sumPaymentsByProfessionalAndYearAndMonth(Long professionalId, int year, int month);

}
