package com.clinica.estetica.repository;

import com.clinica.estetica.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    
    // Busca agendamentos por status
    List<Agendamento> findByStatus(String status);
    
    // Busca agendamentos por data e hora entre um intervalo
    List<Agendamento> findByDataHoraBetween(LocalDateTime start, LocalDateTime end);
    
    // Busca agendamentos por profissional
    List<Agendamento> findByProfissionalIdProfissional(Long idProfissional);
    
    // Busca agendamentos por paciente
    List<Agendamento> findByPacienteIdPaciente(Long idPaciente);
    
    // Busca agendamentos por sala
    List<Agendamento> findBySalaIdSala(Long idSala);
    
 // Consulta personalizada para verificar disponibilidade de profissional e sala
    @Query("SELECT a FROM Agendamento a WHERE a.profissional.idProfissional = :profissionalId AND a.dataHora = :dataHora AND a.status = 'Confirmado'")
    List<Agendamento> findAgendamentosByProfissionalAndDataHora(@Param("profissionalId") Long profissionalId, @Param("dataHora") LocalDateTime dataHora);
    
    @Query("SELECT a FROM Agendamento a WHERE a.sala.idSala = :salaId AND a.dataHora = :dataHora AND a.status = 'Confirmado'")
    List<Agendamento> findAgendamentosBySalaAndDataHora(@Param("salaId") Long salaId, @Param("dataHora") LocalDateTime dataHora);

}
