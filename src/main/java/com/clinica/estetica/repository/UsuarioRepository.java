package com.clinica.estetica.repository;

import com.clinica.estetica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Busca usuário pelo nome de usuário
    Optional<Usuario> findByNomeUsuario(String nomeUsuario);
    
 // Verifica se um nome de usuário já existe
    boolean existsByNomeUsuario(String nomeUsuario);
}
