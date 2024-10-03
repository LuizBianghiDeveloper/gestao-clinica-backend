package com.clinica.estetica.repository;

import com.clinica.estetica.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    
    // Busca profissionais por especialidade
    List<Profissional> findByEspecialidade(String especialidade);
    
    // Busca profissionais cujo nome contém uma string (case insensitive)
    List<Profissional> findByNomeContainingIgnoreCase(String nome);
}
