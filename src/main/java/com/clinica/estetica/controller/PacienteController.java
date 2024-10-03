package com.clinica.estetica.controller;

import com.clinica.estetica.model.Paciente;
import com.clinica.estetica.service.PacienteService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @ApiOperation(value = "Criar um novo paciente", response = Paciente.class)
    @PostMapping
    public ResponseEntity<Paciente> cadastrarPaciente(@Validated @RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteService.cadastrarPaciente(paciente);
        return ResponseEntity.ok(novoPaciente);
    }

    @ApiOperation(value = "Listar tods os pacientes", response = Paciente.class)
    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @ApiOperation(value = "Buscar pacientes por ID", response = Paciente.class)
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);
        return ResponseEntity.ok(paciente);
    }

    @ApiOperation(value = "Atualizar o paciente", response = Paciente.class)
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @Validated @RequestBody Paciente paciente) {
        Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @ApiOperation(value = "Deletar um paciente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPaciente(@PathVariable Long id) {
        pacienteService.removerPaciente(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Pesquisar o paciente pelo Nome", response = Paciente.class)
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Paciente>> buscarPacientesPorNome(@PathVariable String nome) {
        List<Paciente> pacientes = pacienteService.buscarPacientesPorNome(nome);
        return ResponseEntity.ok(pacientes);
    }

    @ApiOperation(value = "Pesquisar o paciente pelo sexo", response = Paciente.class)
    @GetMapping("/sexo/{sexo}")
    public ResponseEntity<List<Paciente>> buscarPacientesPorSexo(@PathVariable String sexo) {
        List<Paciente> pacientes = pacienteService.buscarPacientesPorSexo(sexo);
        return ResponseEntity.ok(pacientes);
    }

    @ApiOperation(value = "Pesquisar o paciente pela data de cadastro", response = Paciente.class)
    @GetMapping("/data-cadastro")
    public ResponseEntity<List<Paciente>> buscarPacientesPorDataCadastro(
            @RequestParam String dataInicio,
            @RequestParam String dataFim) {
        List<Paciente> pacientes = pacienteService.buscarPacientesPorDataCadastro(
                LocalDate.parse(dataInicio),
                LocalDate.parse(dataFim)
        );
        return ResponseEntity.ok(pacientes);
    }
}
