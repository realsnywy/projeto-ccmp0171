package br.com.acolher.api.controllers;

import br.com.acolher.api.dtos.MonthlySettlementResponseDTO;
import br.com.acolher.api.dtos.MonthlySettlementUpdateDTO;
import br.com.acolher.api.entities.MonthlySettlement;
import br.com.acolher.api.services.MonthlySettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monthly-settlements")
public class MonthlySettlementController {

    @Autowired
    private  MonthlySettlementService settlementService;

    @GetMapping
    public ResponseEntity<List<MonthlySettlementResponseDTO>> getAll() {
        try{
            return new ResponseEntity<>(settlementService.readAll(), HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonthlySettlementResponseDTO> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(settlementService.readById(id), HttpStatus.OK);
        }catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<MonthlySettlementResponseDTO> pay(@RequestBody MonthlySettlementUpdateDTO dto) {
        try{
            return new ResponseEntity<>(settlementService.pay(dto), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
