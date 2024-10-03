package com.clinica.estetica.service;

import com.clinica.estetica.model.Relatorio;
import com.clinica.estetica.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    // Criar ou atualizar relatório
    public Relatorio criarRelatorio(Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    // Listar todos os relatórios
    public List<Relatorio> listarRelatorios() {
        return relatorioRepository.findAll();
    }

    // Buscar relatório por ID
    public Optional<Relatorio> buscarRelatorioPorId(Long id) {
        return relatorioRepository.findById(id);
    }

    // Atualizar relatório
    public Optional<Relatorio> atualizarRelatorio(Long id, Relatorio relatorioAtualizado) {
        if (relatorioRepository.existsById(id)) {
            //relatorioAtualizado.setId(id); // Atualiza o ID para garantir que o registro será atualizado
            return Optional.of(relatorioRepository.save(relatorioAtualizado));
        }
        return Optional.empty();
    }

    // Deletar relatório
    public boolean deletarRelatorio(Long id) {
        if (relatorioRepository.existsById(id)) {
            relatorioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
