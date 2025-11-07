package br.com.acolher.api.scheduling;

import br.com.acolher.api.config.EmailSenderService;
import br.com.acolher.api.entities.Payment;
import br.com.acolher.api.enums.PaymentStatus;
import br.com.acolher.api.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PaymentAlertScheduler {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    // Executa 1x por dia às 08:00
    @Scheduled(cron = "0 0 8 * * *")
    public void sendPendingPaymentAlerts() {

        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);

        List<Payment> oldPendingPayments = paymentRepository
                .findByStatusAndPaymentDateBefore(PaymentStatus.PENDING, oneMonthAgo);

        for (Payment payment : oldPendingPayments) {

            String email = payment.getAppointment().getPatient().getRawEmail();
            String subject = "Aviso: Pagamento pendente";
            String body = """
                    Olá, tudo bem?

                    Verificamos que o pagamento referente à sua consulta está pendente há mais de 30 dias.

                    Por favor, se dirija à nossa clinica ou entre em contato conosco pelo telefone: (87): 90000-0000.

                    Atenciosamente,
                    Equipe Acolher.
                    """;

            emailSenderService.sendEmail(email, subject, body);
        }
    }
}
