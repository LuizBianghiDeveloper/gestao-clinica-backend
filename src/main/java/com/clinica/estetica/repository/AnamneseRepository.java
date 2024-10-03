package com.clinica.estetica.repository;

import com.clinica.estetica.model.Anamnese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {
    
    List<Anamnese> findByPacienteIdPaciente(Long idPaciente);
}
