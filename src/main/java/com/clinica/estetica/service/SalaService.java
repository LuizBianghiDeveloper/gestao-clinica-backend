package com.clinica.estetica.service;

import com.clinica.estetica.model.Sala;
import com.clinica.estetica.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public Sala criarSala(Sala sala) {
        return salaRepository.save(sala);
    }

    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    public Optional<Sala> buscarSalaPorId(Long id) {
        return salaRepository.findById(id);
    }

    public Optional<Sala> atualizarSala(Long id, Sala salaAtualizada) {
        if (salaRepository.existsById(id)) {
            salaAtualizada.setIdSala(id);
            return Optional.of(salaRepository.save(salaAtualizada));
        }
        return Optional.empty();
    }

    public boolean deletarSala(Long id) {
        if (salaRepository.existsById(id)) {
            salaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
