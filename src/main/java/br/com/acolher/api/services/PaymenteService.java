package br.com.acolher.api.services;

import br.com.acolher.api.dtos.PaymentCreateDTO;
import br.com.acolher.api.dtos.PaymentResponseDTO;
import br.com.acolher.api.dtos.PaymentUpdateDTO;
import br.com.acolher.api.entities.Appointment;
import br.com.acolher.api.entities.Payment;
import br.com.acolher.api.mappers.PaymentMapper;
import br.com.acolher.api.repositories.AppointmentRepository;
import br.com.acolher.api.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymenteService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public PaymentResponseDTO create(PaymentCreateDTO dto){
        Appointment appointment = appointmentRepository.findById(dto.appointmentId())
                .orElseThrow(() -> new RuntimeException("Consulta com id " + dto.appointmentId() + " não encontrada"));
        Payment payment = PaymentMapper.toEntity(dto, appointment);
        return PaymentMapper.toDTO(paymentRepository.save(payment));
    }

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
        paymentRepository.save(payment);
        return PaymentMapper.toDTO(payment);
    }

    public void delete(Long id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento com id " + id + " não encontrado para deleção"));
        paymentRepository.delete(payment);
    }
}
