package com.clinica.estetica.controller;

import com.clinica.estetica.model.Agendamento;
import com.clinica.estetica.service.AgendamentoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

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

    @ApiOperation(value = "Cadastrar um agendamento", response = Agendamento.class)
    @PostMapping
    public ResponseEntity<?> cadastrarAgendamento(@Validated @RequestBody Agendamento agendamento) {
        try {
            Agendamento novoAgendamento = agendamentoService.cadastrarAgendamento(agendamento);
            return gerarRespostaSucesso(novoAgendamento, "Agendamento registrado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao cadastrar agendamento: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Listar todos os agendamentos", response = Agendamento.class)
    @GetMapping
    public ResponseEntity<?> listarAgendamentos() {
        try {
            List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
            return gerarRespostaSucesso(agendamentos, "Lista de agendamentos", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao listar agendamentos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Pesquisar agendamento por ID", response = Agendamento.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAgendamentoPorId(@PathVariable Long id) {
        try {
            Agendamento agendamento = agendamentoService.buscarAgendamentoPorId(id);
            if (agendamento != null) {
                return gerarRespostaSucesso(agendamento, "Agendamento encontrado", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Agendamento não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar agendamento: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Atualizar agendamento", response = Agendamento.class)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAgendamento(@PathVariable Long id, @Validated @RequestBody Agendamento agendamento) {
        try {
            Agendamento agendamentoAtualizado = agendamentoService.atualizarAgendamento(id, agendamento);
            if (agendamentoAtualizado != null) {
                return gerarRespostaSucesso(agendamentoAtualizado, "Agendamento atualizado com sucesso", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Agendamento não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao atualizar agendamento: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Deletar um agendamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerAgendamento(@PathVariable Long id) {
        try {
            agendamentoService.removerAgendamento(id);
            return gerarRespostaSucesso(null, "Agendamento removido com sucesso", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao remover agendamento: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Pesquisar agendamento por paciente", response = Agendamento.class)
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<?> buscarAgendamentosPorPaciente(@PathVariable Long pacienteId) {
        try {
            List<Agendamento> agendamentos = agendamentoService.buscarAgendamentosPorPaciente(pacienteId);
            return gerarRespostaSucesso(agendamentos, "Agendamentos do paciente", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar agendamentos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
