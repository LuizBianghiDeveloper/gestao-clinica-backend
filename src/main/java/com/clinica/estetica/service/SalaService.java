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

    // Criar ou atualizar sala
    public Sala criarSala(Sala sala) {
        return salaRepository.save(sala);
    }

    // Listar todas as salas
    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    // Buscar sala por ID
    public Optional<Sala> buscarSalaPorId(Long id) {
        return salaRepository.findById(id);
    }

    // Atualizar sala
    public Optional<Sala> atualizarSala(Long id, Sala salaAtualizada) {
        if (salaRepository.existsById(id)) {
            //salaAtualizada.setId(id);
            return Optional.of(salaRepository.save(salaAtualizada));
        }
        return Optional.empty();
    }

    // Deletar sala
    public boolean deletarSala(Long id) {
        if (salaRepository.existsById(id)) {
            salaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
