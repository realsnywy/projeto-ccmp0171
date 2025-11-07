package br.com.acolher.api.controllers;

import br.com.acolher.api.dtos.PaymentResponseDTO;
import br.com.acolher.api.dtos.PaymentUpdateDTO;
import br.com.acolher.api.dtos.UpdatePaymentMethodDTO;
import br.com.acolher.api.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> read(@PathVariable Long id){
        try{
            return new ResponseEntity<>(paymentService.read(id), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyRole('GENERAL_DIRECTO', 'RECEPCIONIST')")
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> readAll(){
        return new ResponseEntity<>(paymentService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PaymentResponseDTO> update(@RequestBody PaymentUpdateDTO dto){
        try{
            return new ResponseEntity<>(paymentService.update(dto), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try{
            paymentService.delete(id);
            return new ResponseEntity<>("Pagamento com id " + id + " deletado com sucesso", HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/method")
    public ResponseEntity<PaymentResponseDTO> updateMethod(@PathVariable Long id, @RequestBody UpdatePaymentMethodDTO dto) {
        try {
            return new ResponseEntity<>(paymentService.updatePaymentMethod(id, dto.paymentMethod()), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping("/{id}/pay")
    public ResponseEntity<PaymentResponseDTO> pay(@PathVariable Long id){
        try{
            return new ResponseEntity<>(paymentService.pay(id), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
