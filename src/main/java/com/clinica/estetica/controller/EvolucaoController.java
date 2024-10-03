package com.clinica.estetica.controller;

import com.clinica.estetica.model.Evolucao;
import com.clinica.estetica.service.EvolucaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/evolucoes")
public class EvolucaoController {

    @Autowired
    private EvolucaoService evolucaoService;

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

    @ApiOperation(value = "Cadastrar evolução de um paciente", response = Evolucao.class)
    @PostMapping
    public ResponseEntity<?> cadastrarEvolucao(@Validated @RequestBody Evolucao evolucao) {
        try {
            Evolucao novaEvolucao = evolucaoService.cadastrarEvolucao(evolucao);
            return gerarRespostaSucesso(novaEvolucao, "Evolução registrada com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao cadastrar evolução: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Listar todas as evoluções", response = Evolucao.class)
    @GetMapping
    public ResponseEntity<?> listarEvolucoes() {
        try {
            List<Evolucao> evolucoes = evolucaoService.listarEvolucoes();
            return gerarRespostaSucesso(evolucoes, "Lista de evoluções", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao listar evoluções: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Pesquisar evolução por ID", response = Evolucao.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEvolucaoPorId(@PathVariable Long id) {
        try {
            Evolucao evolucao = evolucaoService.buscarEvolucaoPorId(id);
            if (evolucao != null) {
                return gerarRespostaSucesso(evolucao, "Evolução encontrada", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Evolução não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar evolução: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Atualizar evolução", response = Evolucao.class)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEvolucao(@PathVariable Long id, @Validated @RequestBody Evolucao evolucao) {
        try {
            Evolucao evolucaoAtualizada = evolucaoService.atualizarEvolucao(id, evolucao);
            if (evolucaoAtualizada != null) {
                return gerarRespostaSucesso(evolucaoAtualizada, "Evolução atualizada com sucesso", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Evolução não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao atualizar evolução: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Deletar uma evolução")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerEvolucao(@PathVariable Long id) {
        try {
            evolucaoService.removerEvolucao(id);
            return gerarRespostaSucesso(null, "Evolução removida com sucesso", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao remover evolução: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Pesquisar evolução por paciente", response = Evolucao.class)
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<?> buscarEvolucoesPorPaciente(@PathVariable Long pacienteId) {
        try {
            List<Evolucao> evolucoes = evolucaoService.buscarEvolucoesPorPaciente(pacienteId);
            return gerarRespostaSucesso(evolucoes, "Evoluções do paciente", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar evoluções: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
