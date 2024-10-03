package com.clinica.estetica.repository;

import com.clinica.estetica.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {
    
    // Busca relatórios por tipo
    List<Relatorio> findByTipoRelatorio(String tipoRelatorio);
    
    // Busca relatórios gerados após uma determinada data
    List<Relatorio> findByDataGeracaoAfter(LocalDate data);
}
