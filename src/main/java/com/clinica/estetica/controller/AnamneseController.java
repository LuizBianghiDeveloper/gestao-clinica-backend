package com.clinica.estetica.controller;

import com.clinica.estetica.model.Anamnese;
import com.clinica.estetica.service.AnamneseService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anamneses")
public class AnamneseController {

    @Autowired
    private AnamneseService anamneseService;

    @ApiOperation(value = "Cadastrar uma anamnese", response = Anamnese.class)
    @PostMapping
    public ResponseEntity<Anamnese> cadastrarAnamnese(@Validated @RequestBody Anamnese anamnese) {
        Anamnese novaAnamnese = anamneseService.cadastrarAnamnese(anamnese);
        return ResponseEntity.ok(novaAnamnese);
    }

    @ApiOperation(value = "Listar todas anamneses", response = Anamnese.class)
    @GetMapping
    public ResponseEntity<List<Anamnese>> listarAnamneses() {
        List<Anamnese> anamneses = anamneseService.listarAnamneses();
        return ResponseEntity.ok(anamneses);
    }

    @ApiOperation(value = "Buscar uma anamnese por ID", response = Anamnese.class)
    @GetMapping("/{id}")
    public ResponseEntity<Anamnese> buscarAnamnesePorId(@PathVariable Long id) {
        Anamnese anamnese = anamneseService.buscarAnamnesePorId(id);
        return ResponseEntity.ok(anamnese);
    }

    @ApiOperation(value = "Atualizar uma anamnese", response = Anamnese.class)
    @PutMapping("/{id}")
    public ResponseEntity<Anamnese> atualizarAnamnese(@PathVariable Long id, @Validated @RequestBody Anamnese anamnese) {
        Anamnese anamneseAtualizada = anamneseService.atualizarAnamnese(id, anamnese);
        return ResponseEntity.ok(anamneseAtualizada);
    }

    @ApiOperation(value = "Deletar uma anamnese")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAnamnese(@PathVariable Long id) {
        anamneseService.removerAnamnese(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Pesquisar uma anamnese por paciente", response = Anamnese.class)
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Anamnese>> buscarAnamnesesPorPaciente(@PathVariable Long pacienteId) {
        List<Anamnese> anamneses = anamneseService.buscarAnamnesePorPaciente(pacienteId);
        return ResponseEntity.ok(anamneses);
    }
}
