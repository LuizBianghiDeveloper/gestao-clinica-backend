package com.clinica.estetica.controller;

import com.clinica.estetica.model.Paciente;
import com.clinica.estetica.service.PacienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    private ResponseEntity<?> gerarRespostaSucesso(Object data, String mensagem, HttpStatus status) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("codigo", status.value());
        resposta.put("mensagem", mensagem);
        resposta.put("data", data);
        return ResponseEntity.status(status).body(resposta);
    }

    private ResponseEntity<?> gerarRespostaErro(String mensagem, HttpStatus status) {
        Map<String, String> respostaErro = new HashMap<>();
        respostaErro.put("codigo", String.valueOf(status.value()));
        respostaErro.put("mensagem", mensagem);
        return ResponseEntity.status(status).body(respostaErro);
    }

    @ApiOperation(value = "Criar um novo paciente", response = Paciente.class)
    @PostMapping
    public ResponseEntity<?> cadastrarPaciente(@Validated @RequestBody Paciente paciente) {
        try {
            Paciente novoPaciente = pacienteService.cadastrarPaciente(paciente);
            return gerarRespostaSucesso(novoPaciente, "Paciente criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao criar o paciente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Listar todos os pacientes", response = Paciente.class)
    @GetMapping
    public ResponseEntity<?> listarPacientes() {
        try {
            List<Paciente> pacientes = pacienteService.listarPacientes();
            return gerarRespostaSucesso(pacientes, "Lista de pacientes", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao listar pacientes: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Buscar paciente por ID", response = Paciente.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPacientePorId(@PathVariable Long id) {
        try {
            Paciente paciente = pacienteService.buscarPacientePorId(id);
            if (paciente != null) {
                return gerarRespostaSucesso(paciente, "Paciente encontrado", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Paciente não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar paciente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Atualizar o paciente", response = Paciente.class)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPaciente(@PathVariable Long id, @Validated @RequestBody Paciente paciente) {
        try {
            Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);
            if (pacienteAtualizado != null) {
                return gerarRespostaSucesso(pacienteAtualizado, "Paciente atualizado com sucesso", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Paciente não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao atualizar o paciente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Deletar um paciente")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerPaciente(@PathVariable Long id) {
        try {
            pacienteService.removerPaciente(id);
            return gerarRespostaSucesso(null, "Paciente removido com sucesso", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao remover paciente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Pesquisar pacientes por nome", response = Paciente.class)
    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> buscarPacientesPorNome(@PathVariable String nome) {
        try {
            List<Paciente> pacientes = pacienteService.buscarPacientesPorNome(nome);
            return gerarRespostaSucesso(pacientes, "Pacientes encontrados", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar pacientes: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Pesquisar pacientes por sexo", response = Paciente.class)
    @GetMapping("/sexo/{sexo}")
    public ResponseEntity<?> buscarPacientesPorSexo(@PathVariable String sexo) {
        try {
            List<Paciente> pacientes = pacienteService.buscarPacientesPorSexo(sexo);
            return gerarRespostaSucesso(pacientes, "Pacientes encontrados", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar pacientes: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Pesquisar pacientes pela data de cadastro", response = Paciente.class)
    @GetMapping("/data-cadastro")
    public ResponseEntity<?> buscarPacientesPorDataCadastro(
            @RequestParam String dataInicio,
            @RequestParam String dataFim) {
        try {
            List<Paciente> pacientes = pacienteService.buscarPacientesPorDataCadastro(
                    LocalDate.parse(dataInicio),
                    LocalDate.parse(dataFim)
            );
            return gerarRespostaSucesso(pacientes, "Pacientes encontrados", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar pacientes: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
