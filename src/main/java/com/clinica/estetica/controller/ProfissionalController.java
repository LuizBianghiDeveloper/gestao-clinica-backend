package com.clinica.estetica.controller;

import com.clinica.estetica.model.Profissional;
import com.clinica.estetica.service.ProfissionalService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

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

    @ApiOperation(value = "Criar um novo profissional", response = Profissional.class)
    @PostMapping
    public ResponseEntity<?> cadastrarProfissional(@Validated @RequestBody Profissional profissional) {
        try {
            Profissional novoProfissional = profissionalService.criarProfissional(profissional);
            return gerarRespostaSucesso(novoProfissional, "Profissional criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao criar o profissional: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Listar os profissionais", response = List.class)
    @GetMapping
    public ResponseEntity<?> listarProfissionais() {
        try {
            List<Profissional> profissionais = profissionalService.listarProfissionais();
            return gerarRespostaSucesso(profissionais, "Lista de profissionais", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao listar os profissionais: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Buscar profissional por ID", response = Profissional.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProfissionalPorId(@PathVariable Long id) {
        try {
            Optional<Profissional> profissional = profissionalService.buscarProfissionalPorId(id);

            if (profissional.isPresent()) {
                return gerarRespostaSucesso(profissional.get(), "Profissional encontrado", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Profissional não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar o profissional: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Atualizar um profissional", response = Profissional.class)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProfissional(@PathVariable Long id, @Validated @RequestBody Profissional profissional) {
        try {
            Optional<Profissional> profissionalAtualizado = profissionalService.atualizarProfissional(id, profissional);

            if (profissionalAtualizado.isPresent()) {
                return gerarRespostaSucesso(profissionalAtualizado.get(), "Profissional atualizado com sucesso", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Profissional com ID " + id + " não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao atualizar o profissional: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Deletar um profissional")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerProfissional(@PathVariable Long id) {
        try {
            if (profissionalService.deletarProfissional(id)) {
                return gerarRespostaSucesso(null, "Profissional removido com sucesso", HttpStatus.NO_CONTENT);
            } else {
                return gerarRespostaErro("Profissional não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao remover o profissional: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
