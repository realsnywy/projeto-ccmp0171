package br.com.acolher.api.controllers;

import br.com.acolher.api.dtos.PatientGuardianCreateDTO;
import br.com.acolher.api.dtos.PatientGuardianUpdateDTO;
import br.com.acolher.api.services.PatientGuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guardians")
public class PatientGuardianController {
    @Autowired
    private PatientGuardianService patientGuardianService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PatientGuardianCreateDTO patientguardianCreateDTO) {
        return new ResponseEntity<>(patientGuardianService.create(patientguardianCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        try{
            return new ResponseEntity<>(patientGuardianService.readAll(), HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(patientGuardianService.read(id), HttpStatus.OK);
        } catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PatientGuardianUpdateDTO patientguardianUpdateDTO) {
        try {
            return new ResponseEntity<>(patientGuardianService.update(patientguardianUpdateDTO), HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            patientGuardianService.delete(id);
            return new ResponseEntity<>("Responsável deletado com sucesso",HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
