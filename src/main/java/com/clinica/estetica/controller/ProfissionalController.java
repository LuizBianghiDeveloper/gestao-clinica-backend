package com.clinica.estetica.controller;

import com.clinica.estetica.model.Profissional;
import com.clinica.estetica.service.ProfissionalService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @ApiOperation(value = "Criar um novo profissional", response = Profissional.class)
    @PostMapping
    public ResponseEntity<Profissional> cadastrarProfissional(@Validated @RequestBody Profissional profissional) {
        Profissional novoProfissional = profissionalService.criarProfissional(profissional);
        return ResponseEntity.ok(novoProfissional);
    }

    @ApiOperation(value = "Listar os profissionais", response = Profissional.class)
    @GetMapping
    public ResponseEntity<List<Profissional>> listarProfissionais() {
        List<Profissional> profissionais = profissionalService.listarProfissionais();
        return ResponseEntity.ok(profissionais);
    }

    @ApiOperation(value = "Buscar os profissionais por ID", response = Profissional.class)
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Profissional>> buscarProfissionalPorId(@PathVariable Long id) {
        Optional<Profissional> profissional = profissionalService.buscarProfissionalPorId(id);
        return ResponseEntity.ok(profissional);
    }

    @ApiOperation(value = "Atualizar um profissional", response = Profissional.class)
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Profissional>> atualizarProfissional(@PathVariable Long id, @Validated @RequestBody Profissional profissional) {
        Optional<Profissional> profissionalAtualizado = profissionalService.atualizarProfissional(id, profissional);
        return ResponseEntity.ok(profissionalAtualizado);
    }

    @ApiOperation(value = "Deletar um profissional")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerProfissional(@PathVariable Long id) {
        profissionalService.deletarProfissional(id);
        return ResponseEntity.noContent().build();
    }
}
