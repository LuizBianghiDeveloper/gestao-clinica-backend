package com.clinica.estetica.repository;

import com.clinica.estetica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    Optional<Paciente> findByEmail(String email);

    List<Paciente> findByNomeContainingIgnoreCase(String nome);

    List<Paciente> findByDataCadastroBetween(java.time.LocalDate dataInicio, java.time.LocalDate dataFim);

    List<Paciente> findBySexo(String sexo);

    Optional<Paciente> findByTelefone(String telefone);

    // Você pode adicionar mais métodos personalizados conforme necessário.
}
