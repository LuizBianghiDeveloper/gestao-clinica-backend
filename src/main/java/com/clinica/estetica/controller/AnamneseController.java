package com.clinica.estetica.controller;

import com.clinica.estetica.model.Anamnese;
import com.clinica.estetica.service.AnamneseService;
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
@RequestMapping("/api/anamneses")
public class AnamneseController {

    @Autowired
    private AnamneseService anamneseService;

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

    @ApiOperation(value = "Cadastrar uma anamnese", response = Anamnese.class)
    @PostMapping
    public ResponseEntity<?> cadastrarAnamnese(@Validated @RequestBody Anamnese anamnese) {
        try {
            Anamnese novaAnamnese = anamneseService.cadastrarAnamnese(anamnese);
            return gerarRespostaSucesso(novaAnamnese, "Anamnese registrada com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao cadastrar anamnese: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Listar todas as anamneses", response = Anamnese.class)
    @GetMapping
    public ResponseEntity<?> listarAnamneses() {
        try {
            List<Anamnese> anamneses = anamneseService.listarAnamneses();
            return gerarRespostaSucesso(anamneses, "Lista de anamneses", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao listar anamneses: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Buscar uma anamnese por ID", response = Anamnese.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAnamnesePorId(@PathVariable Long id) {
        try {
            Anamnese anamnese = anamneseService.buscarAnamnesePorId(id);
            if (anamnese != null) {
                return gerarRespostaSucesso(anamnese, "Anamnese encontrada", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Anamnese não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar anamnese: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Atualizar uma anamnese", response = Anamnese.class)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAnamnese(@PathVariable Long id, @Validated @RequestBody Anamnese anamnese) {
        try {
            Anamnese anamneseAtualizada = anamneseService.atualizarAnamnese(id, anamnese);
            if (anamneseAtualizada != null) {
                return gerarRespostaSucesso(anamneseAtualizada, "Anamnese atualizada com sucesso", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Anamnese não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao atualizar anamnese: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Deletar uma anamnese")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerAnamnese(@PathVariable Long id) {
        try {
            anamneseService.removerAnamnese(id);
            return gerarRespostaSucesso(null, "Anamnese removida com sucesso", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao remover anamnese: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Pesquisar uma anamnese por paciente", response = Anamnese.class)
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<?> buscarAnamnesesPorPaciente(@PathVariable Long pacienteId) {
        try {
            List<Anamnese> anamneses = anamneseService.buscarAnamnesePorPaciente(pacienteId);
            return gerarRespostaSucesso(anamneses, "Anamneses do paciente", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar anamneses: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
