package br.com.acolher.api.controllers;

import br.com.acolher.api.dtos.UserCreateDTO;
import br.com.acolher.api.dtos.UserResponseDTO;
import br.com.acolher.api.dtos.UserUpdateDTO;
import br.com.acolher.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyRole('GENERAL_DIRECTOR', 'RECEPCIONIST')")
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserCreateDTO userCreateDTO) {
        return new ResponseEntity<>(userService.create(userCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('GENERAL_DIRECTOR', 'RECEPCIONIST')")
    public ResponseEntity<?> readAll() {
        try {
            return new ResponseEntity<>(userService.readAll(), HttpStatus.OK);
        }catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('GENERAL_DIRECTOR', 'RECEPCIONIST')")
    public ResponseEntity<?> readById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(userService.read(id), HttpStatus.OK);
        }catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping
    @PreAuthorize("hasAnyRole('GENERAL_DIRECTOR', 'RECEPCIONIST')")
    public ResponseEntity<?> update(@RequestBody UserUpdateDTO userUpdateDTO) {
        try {
            return new ResponseEntity<>(userService.update(userUpdateDTO), HttpStatus.OK);
        }catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('GENERAL_DIRECTOR', 'RECEPCIONIST')")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>("Usuário deletado com sucesso",HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyRole('GENERAL_DIRECTOR', 'RECEPCIONIST')")
    @GetMapping("/get-professionals")
    public ResponseEntity<List<UserResponseDTO>> getAllProfessionals() {
        try{
            return new ResponseEntity<>(userService.getAllProfessionals(), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
