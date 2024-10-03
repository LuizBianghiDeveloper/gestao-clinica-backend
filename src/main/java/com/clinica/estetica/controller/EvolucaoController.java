package com.clinica.estetica.controller;

import com.clinica.estetica.model.Evolucao;
import com.clinica.estetica.service.EvolucaoService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evolucoes")
public class EvolucaoController {

    @Autowired
    private EvolucaoService evolucaoService;

    @ApiOperation(value = "Cadastrar evolução de um paciente", response = Evolucao.class)
    @PostMapping
    public ResponseEntity<Evolucao> cadastrarEvolucao(@Validated @RequestBody Evolucao evolucao) {
        Evolucao novaEvolucao = evolucaoService.cadastrarEvolucao(evolucao);
        return ResponseEntity.ok(novaEvolucao);
    }

    @ApiOperation(value = "Listar todas evoluções", response = Evolucao.class)
    @GetMapping
    public ResponseEntity<List<Evolucao>> listarEvolucoes() {
        List<Evolucao> evolucoes = evolucaoService.listarEvolucoes();
        return ResponseEntity.ok(evolucoes);
    }

    @ApiOperation(value = "Pesquiar evolução por ID", response = Evolucao.class)
    @GetMapping("/{id}")
    public ResponseEntity<Evolucao> buscarEvolucaoPorId(@PathVariable Long id) {
        Evolucao evolucao = evolucaoService.buscarEvolucaoPorId(id);
        return ResponseEntity.ok(evolucao);
    }

    @ApiOperation(value = "Atualizar evolução", response = Evolucao.class)
    @PutMapping("/{id}")
    public ResponseEntity<Evolucao> atualizarEvolucao(@PathVariable Long id, @Validated @RequestBody Evolucao evolucao) {
        Evolucao evolucaoAtualizada = evolucaoService.atualizarEvolucao(id, evolucao);
        return ResponseEntity.ok(evolucaoAtualizada);
    }

    @ApiOperation(value = "Deletar uma evolução")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEvolucao(@PathVariable Long id) {
        evolucaoService.removerEvolucao(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Pesquisar evolução por paciente", response = Evolucao.class)
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Evolucao>> buscarEvolucoesPorPaciente(@PathVariable Long pacienteId) {
        List<Evolucao> evolucoes = evolucaoService.buscarEvolucoesPorPaciente(pacienteId);
        return ResponseEntity.ok(evolucoes);
    }
}
