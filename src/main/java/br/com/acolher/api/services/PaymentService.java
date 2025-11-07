package br.com.acolher.api.services;

import br.com.acolher.api.dtos.PaymentResponseDTO;
import br.com.acolher.api.dtos.PaymentUpdateDTO;
import br.com.acolher.api.entities.Payment;
import br.com.acolher.api.enums.PaymentMethod;
import br.com.acolher.api.enums.PaymentStatus;
import br.com.acolher.api.mappers.PaymentMapper;
import br.com.acolher.api.repositories.AppointmentRepository;
import br.com.acolher.api.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<PaymentResponseDTO> readAll(){
        return paymentRepository.findAll().stream()
                .map(PaymentMapper::toDTO)
                .toList();
    }

    public PaymentResponseDTO read(Long id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento com id " + id + " não encontrado"));
        return PaymentMapper.toDTO(payment);
    }

    public PaymentResponseDTO update(PaymentUpdateDTO dto){
        Payment payment = paymentRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Pagamento com id " + dto.id() + " não encontrado"));
        payment.setStatus(dto.status());
        payment.setMethod(dto.method());
        payment.setPaymentDate(dto.paymentDate());
        payment.setAmount(dto.amount());
        paymentRepository.save(payment);
        return PaymentMapper.toDTO(payment);
    }

    public void delete(Long id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento com id " + id + " não encontrado para deleção"));
        paymentRepository.delete(payment);
    }

    public PaymentResponseDTO updatePaymentMethod(Long id, PaymentMethod method) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        if (payment.getStatus() == PaymentStatus.PAID) {
            throw new RuntimeException("Não é possível alterar o método após o pagamento.");
        }

        payment.setMethod(method);
        paymentRepository.save(payment);

        return PaymentMapper.toDTO(payment);
    }

    public PaymentResponseDTO pay(Long id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Pagamento com id " + id + " não encontrado para pagamento"));

        if (payment.getStatus() == PaymentStatus.PAID) {
            throw new IllegalStateException("Este pagamento já foi realizado.");
        }

        payment.setStatus(PaymentStatus.PAID);
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
        return PaymentMapper.toDTO(payment);
    }
}
