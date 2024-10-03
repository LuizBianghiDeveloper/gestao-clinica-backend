package com.clinica.estetica.controller;

import com.clinica.estetica.model.Agendamento;
import com.clinica.estetica.service.AgendamentoService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @ApiOperation(value = "Cadastrar um agendamento", response = Agendamento.class)
    @PostMapping
    public ResponseEntity<Agendamento> cadastrarAgendamento(@Validated @RequestBody Agendamento agendamento) {
        Agendamento novoAgendamento = agendamentoService.cadastrarAgendamento(agendamento);
        return ResponseEntity.ok(novoAgendamento);
    }

    @ApiOperation(value = "Listar todos os agendamentos", response = Agendamento.class)
    @GetMapping
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @ApiOperation(value = "Pesquisar agendamento por ID", response = Agendamento.class)
    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarAgendamentoPorId(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.buscarAgendamentoPorId(id);
        return ResponseEntity.ok(agendamento);
    }

    @ApiOperation(value = "Atualizar agendamento", response = Agendamento.class)
    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id, @Validated @RequestBody Agendamento agendamento) {
        Agendamento agendamentoAtualizado = agendamentoService.atualizarAgendamento(id, agendamento);
        return ResponseEntity.ok(agendamentoAtualizado);
    }

    @ApiOperation(value = "Deletar um agendamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAgendamento(@PathVariable Long id) {
        agendamentoService.removerAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Pesquisar agendamento por paciente", response = Agendamento.class)
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Agendamento>> buscarAgendamentosPorPaciente(@PathVariable Long pacienteId) {
        List<Agendamento> agendamentos = agendamentoService.buscarAgendamentosPorPaciente(pacienteId);
        return ResponseEntity.ok(agendamentos);
    }
}
