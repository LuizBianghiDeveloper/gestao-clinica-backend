package com.clinica.estetica.service;

import com.clinica.estetica.model.Agendamento;
import com.clinica.estetica.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento cadastrarAgendamento(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public Agendamento buscarAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com ID: " + id));
    }

    public Agendamento atualizarAgendamento(Long id, Agendamento agendamento) {
        Agendamento existente = buscarAgendamentoPorId(id);
        existente.setDataHora(agendamento.getDataHora());
        existente.setPaciente(agendamento.getPaciente());
        return agendamentoRepository.save(existente);
    }

    public void removerAgendamento(Long id) {
        Agendamento existente = buscarAgendamentoPorId(id);
        agendamentoRepository.delete(existente);
    }

    public List<Agendamento> buscarAgendamentosPorPaciente(Long pacienteId) {
        return agendamentoRepository.findByPacienteIdPaciente(pacienteId);
    }

    public List<Agendamento> buscarAgendamentosPorData(java.time.LocalDate dataInicio, java.time.LocalDate dataFim) {
        return agendamentoRepository.findByDataHoraBetween(dataInicio.atStartOfDay(), dataFim.atTime(23, 59, 59));
    }
}
