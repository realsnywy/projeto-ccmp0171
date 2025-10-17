package br.com.acolher.api.controllers;

import br.com.acolher.api.dtos.PatientCreateDTO;
import br.com.acolher.api.dtos.PatientUpdateDTO;
import br.com.acolher.api.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PatientCreateDTO patientCreateDTO) {
        return new ResponseEntity<>(patientService.create(patientCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        try{
            return new ResponseEntity<>(patientService.readAll(), HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(patientService.read(id), HttpStatus.OK);
        } catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PatientUpdateDTO patientUpdateDTO) {
        try {
            return new ResponseEntity<>(patientService.update(patientUpdateDTO), HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            patientService.delete(id);
            return new ResponseEntity<>("Paciente deletado com sucesso",HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
