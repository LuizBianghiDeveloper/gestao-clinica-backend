package com.clinica.estetica.service;

import com.clinica.estetica.model.Paciente;
import com.clinica.estetica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pelas operações de lógica de negócio da entidade Paciente.
 */
@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente cadastrarPaciente(Paciente paciente) {
        Optional<Paciente> existente = pacienteRepository.findByEmail(paciente.getEmail());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Já existe um paciente com este email.");
        }
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));
    }
    
    public Paciente atualizarPaciente(Long id, Paciente paciente) {
        Paciente existente = buscarPacientePorId(id);
        
        existente.setNome(paciente.getNome());
        existente.setDataNascimento(paciente.getDataNascimento());
        existente.setSexo(paciente.getSexo());
        existente.setTelefone(paciente.getTelefone());
        existente.setEmail(paciente.getEmail());
        existente.setEndereco(paciente.getEndereco());
        
        return pacienteRepository.save(existente);
    }

    public void removerPaciente(Long id) {
        Paciente existente = buscarPacientePorId(id);
        pacienteRepository.delete(existente);
    }

    public List<Paciente> buscarPacientesPorNome(String nome) {
        return pacienteRepository.findByNomeContainingIgnoreCase(nome);
    }


    public List<Paciente> buscarPacientesPorSexo(String sexo) {
        return pacienteRepository.findBySexo(sexo);
    }


    public List<Paciente> buscarPacientesPorDataCadastro(java.time.LocalDate dataInicio, java.time.LocalDate dataFim) {
        return pacienteRepository.findByDataCadastroBetween(dataInicio, dataFim);
    }
}
