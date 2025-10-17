package br.com.acolher.api.controllers;

import br.com.acolher.api.dtos.UserCreateDTO;
import br.com.acolher.api.dtos.UserResponseDTO;
import br.com.acolher.api.dtos.UserUpdateDTO;
import br.com.acolher.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService usuarioService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserCreateDTO userCreateDTO) {
        return new ResponseEntity<>(usuarioService.create(userCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        try {
            return new ResponseEntity<>(usuarioService.readAll(), HttpStatus.OK);
        }catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(usuarioService.read(id), HttpStatus.OK);
        }catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserUpdateDTO userUpdateDTO) {
        try {
            return new ResponseEntity<>(usuarioService.update(userUpdateDTO), HttpStatus.OK);
        }catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            usuarioService.delete(id);
            return new ResponseEntity<>("Usuário deletado com sucesso",HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
