package com.clinica.estetica.service;

import com.clinica.estetica.model.Anamnese;
import com.clinica.estetica.repository.AnamneseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnamneseService {

    @Autowired
    private AnamneseRepository anamneseRepository;

    public Anamnese cadastrarAnamnese(Anamnese anamnese) {
        return anamneseRepository.save(anamnese);
    }

    public List<Anamnese> listarAnamneses() {
        return anamneseRepository.findAll();
    }

    public Anamnese buscarAnamnesePorId(Long id) {
        return anamneseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anamnese não encontrada com ID: " + id));
    }

    public Anamnese atualizarAnamnese(Long id, Anamnese anamnese) {
        Anamnese existente = buscarAnamnesePorId(id);
        existente.setObservacoes(anamnese.getObservacoes());
        return anamneseRepository.save(existente);
    }

    public void removerAnamnese(Long id) {
        Anamnese existente = buscarAnamnesePorId(id);
        anamneseRepository.delete(existente);
    }

    public List<Anamnese> buscarAnamnesePorPaciente(Long pacienteId) {
        return anamneseRepository.findByPacienteIdPaciente(pacienteId);
    }
}
