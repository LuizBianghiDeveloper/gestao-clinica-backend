package com.clinica.estetica.controller;

import com.clinica.estetica.model.Relatorio;
import com.clinica.estetica.service.RelatorioService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @ApiOperation(value = "Criar um novo relatorio", response = Relatorio.class)
    @PostMapping
    public ResponseEntity<Relatorio> criarRelatorio(@RequestBody Relatorio relatorio) {
        Relatorio novoRelatorio = relatorioService.criarRelatorio(relatorio);
        return ResponseEntity.ok(novoRelatorio);
    }

    @ApiOperation(value = "Listar os relatórios", response = Relatorio.class)
    @GetMapping
    public ResponseEntity<List<Relatorio>> listarRelatorios() {
        List<Relatorio> relatorios = relatorioService.listarRelatorios();
        return ResponseEntity.ok(relatorios);
    }

    @ApiOperation(value = "Buscar um relatorio", response = Relatorio.class)
    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> buscarRelatorioPorId(@PathVariable Long id) {
        Optional<Relatorio> relatorio = relatorioService.buscarRelatorioPorId(id);
        return relatorio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Atualizar um relatorio", response = Relatorio.class)
    @PutMapping("/{id}")
    public ResponseEntity<Relatorio> atualizarRelatorio(@PathVariable Long id, @RequestBody Relatorio relatorioAtualizado) {
        Optional<Relatorio> relatorio = relatorioService.atualizarRelatorio(id, relatorioAtualizado);
        return relatorio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Deletar um relatório")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRelatorio(@PathVariable Long id) {
        if (relatorioService.deletarRelatorio(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
