package com.clinica.estetica.controller;

import com.clinica.estetica.model.Sala;
import com.clinica.estetica.service.SalaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

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

    @ApiOperation(value = "Criar uma nova sala", response = Sala.class)
    @PostMapping
    public ResponseEntity<?> cadastrarSala(@Validated @RequestBody Sala sala) {
        try {
            Sala novaSala = salaService.criarSala(sala);
            return gerarRespostaSucesso(novaSala, "Sala criada com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao criar a sala: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Listar todas as salas", response = Sala.class)
    @GetMapping
    public ResponseEntity<?> listarSalas() {
        try {
            List<Sala> salas = salaService.listarSalas();
            return gerarRespostaSucesso(salas, "Lista de salas", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao listar as salas: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Buscar salas por ID", response = Sala.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarSalaPorId(@PathVariable Long id) {
        try {
            Optional<Sala> sala = salaService.buscarSalaPorId(id);

            if (sala.isPresent()) {
                return gerarRespostaSucesso(sala.get(), "Sala encontrada", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Sala não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar a sala: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Atualizar uma sala", response = Sala.class)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarSala(@PathVariable Long id, @Validated @RequestBody Sala sala) {
        try {
            Optional<Sala> salaAtualizada = salaService.atualizarSala(id, sala);

            if (salaAtualizada.isPresent()) {
                return gerarRespostaSucesso(salaAtualizada.get(), "Sala atualizada com sucesso", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Sala com ID " + id + " não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao atualizar a sala: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Deletar uma sala")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerSala(@PathVariable Long id) {
        try {
            salaService.deletarSala(id);
            return gerarRespostaSucesso(null, "Sala removida com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao remover a sala: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
