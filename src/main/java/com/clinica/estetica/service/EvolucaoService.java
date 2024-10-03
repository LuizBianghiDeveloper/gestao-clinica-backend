package com.clinica.estetica.service;

import com.clinica.estetica.model.Evolucao;
import com.clinica.estetica.repository.EvolucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvolucaoService {

    @Autowired
    private EvolucaoRepository evolucaoRepository;

    public Evolucao cadastrarEvolucao(Evolucao evolucao) {
        return evolucaoRepository.save(evolucao);
    }

    public List<Evolucao> listarEvolucoes() {
        return evolucaoRepository.findAll();
    }

    public Evolucao buscarEvolucaoPorId(Long id) {
        return evolucaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evolução não encontrada com ID: " + id));
    }

    public Evolucao atualizarEvolucao(Long id, Evolucao evolucao) {
        Evolucao existente = buscarEvolucaoPorId(id);
        existente.setObservacoes(evolucao.getObservacoes());
        existente.setDataEvolucao(evolucao.getDataEvolucao());
        return evolucaoRepository.save(existente);
    }

    public void removerEvolucao(Long id) {
        Evolucao existente = buscarEvolucaoPorId(id);
        evolucaoRepository.delete(existente);
    }

    public List<Evolucao> buscarEvolucoesPorPaciente(Long pacienteId) {
        return evolucaoRepository.findByPacienteIdPaciente(pacienteId);
    }
}
