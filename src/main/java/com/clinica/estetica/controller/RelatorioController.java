package com.clinica.estetica.controller;

import com.clinica.estetica.model.Relatorio;
import com.clinica.estetica.service.RelatorioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

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

    @ApiOperation(value = "Criar um novo relatorio", response = Relatorio.class)
    @PostMapping
    public ResponseEntity<?> criarRelatorio(@RequestBody Relatorio relatorio) {
        try {
            Relatorio novoRelatorio = relatorioService.criarRelatorio(relatorio);
            return gerarRespostaSucesso(novoRelatorio, "Relatório criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao criar o relatório: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Listar os relatórios", response = List.class)
    @GetMapping
    public ResponseEntity<?> listarRelatorios() {
        try {
            List<Relatorio> relatorios = relatorioService.listarRelatorios();
            return gerarRespostaSucesso(relatorios, "Lista de relatórios", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao listar os relatórios: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Buscar um relatório por ID", response = Relatorio.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRelatorioPorId(@PathVariable Long id) {
        try {
            Optional<Relatorio> relatorio = relatorioService.buscarRelatorioPorId(id);

            if (relatorio.isPresent()) {
                return gerarRespostaSucesso(relatorio.get(), "Relatório encontrado", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Relatório não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar o relatório: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Atualizar um relatório", response = Relatorio.class)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarRelatorio(@PathVariable Long id, @RequestBody Relatorio relatorioAtualizado) {
        try {
            Optional<Relatorio> relatorio = relatorioService.atualizarRelatorio(id, relatorioAtualizado);

            if (relatorio.isPresent()) {
                return gerarRespostaSucesso(relatorio.get(), "Relatório atualizado com sucesso", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Relatório com ID " + id + " não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao atualizar o relatório: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Deletar um relatório")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarRelatorio(@PathVariable Long id) {
        try {
            if (relatorioService.deletarRelatorio(id)) {
                return gerarRespostaSucesso(null, "Relatório removido com sucesso", HttpStatus.NO_CONTENT);
            } else {
                return gerarRespostaErro("Relatório não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao remover o relatório: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
