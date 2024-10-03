package com.clinica.estetica.controller;

import com.clinica.estetica.model.Sala;
import com.clinica.estetica.service.SalaService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @ApiOperation(value = "Criar uma nova sala", response = Sala.class)
    @PostMapping
    public ResponseEntity<Sala> cadastrarSala(@Validated @RequestBody Sala sala) {
        Sala novaSala = salaService.criarSala(sala);
        return ResponseEntity.ok(novaSala);
    }

    @ApiOperation(value = "Lista todas as salas", response = Sala.class)
    @GetMapping
    public ResponseEntity<List<Sala>> listarSalas() {
        List<Sala> salas = salaService.listarSalas();
        return ResponseEntity.ok(salas);
    }

    @ApiOperation(value = "Buscar salas por ID", response = Sala.class)
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sala>> buscarSalaPorId(@PathVariable Long id) {
        Optional<Sala> sala = salaService.buscarSalaPorId(id);
        return ResponseEntity.ok(sala);
    }

    @ApiOperation(value = "Atualizar uma sala", response = Sala.class)
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Sala>> atualizarSala(@PathVariable Long id, @Validated @RequestBody Sala sala) {
        Optional<Sala> salaAtualizada = salaService.atualizarSala(id, sala);
        return ResponseEntity.ok(salaAtualizada);
    }

    @ApiOperation(value = "Deletar uma sala")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerSala(@PathVariable Long id) {
        salaService.deletarSala(id);
        return ResponseEntity.noContent().build();
    }
}
