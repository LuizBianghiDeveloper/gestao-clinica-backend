package com.clinica.estetica.service;

import com.clinica.estetica.model.Profissional;
import com.clinica.estetica.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Profissional criarProfissional(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    public List<Profissional> listarProfissionais() {
        return profissionalRepository.findAll();
    }

    public Optional<Profissional> buscarProfissionalPorId(Long id) {
        return profissionalRepository.findById(id);
    }

    public Optional<Profissional> atualizarProfissional(Long id, Profissional profissionalAtualizado) {
        if (profissionalRepository.existsById(id)) {
            profissionalAtualizado.setIdProfissional(id); 
            return Optional.of(profissionalRepository.save(profissionalAtualizado));
        }
        return Optional.empty();
    }

    public boolean deletarProfissional(Long id) {
        if (profissionalRepository.existsById(id)) {
            profissionalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
