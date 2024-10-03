package com.clinica.estetica.repository;

import com.clinica.estetica.model.Evolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvolucaoRepository extends JpaRepository<Evolucao, Long> {
    
    List<Evolucao> findByPacienteIdPaciente(Long idPaciente);
}
