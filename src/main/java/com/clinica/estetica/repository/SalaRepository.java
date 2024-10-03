package com.clinica.estetica.repository;

import com.clinica.estetica.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    
    // Busca sala pelo número
    Optional<Sala> findByNumero(String numero);
    
    // Busca salas por tipo
    List<Sala> findByTipo(String tipo);
}
