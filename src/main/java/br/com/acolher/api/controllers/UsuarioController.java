package br.com.acolher.api.controllers;

import br.com.acolher.api.dtos.UsuarioCreateDTO;
import br.com.acolher.api.dtos.UsuarioResponseDTO;
import br.com.acolher.api.dtos.UsuarioUpdateDTO;
import br.com.acolher.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        return new ResponseEntity<>(usuarioService.create(usuarioCreateDTO), HttpStatus.CREATED);
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

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        try {
            return new ResponseEntity<>(usuarioService.update(id, usuarioUpdateDTO), HttpStatus.OK);
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
