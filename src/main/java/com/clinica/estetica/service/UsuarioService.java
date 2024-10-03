package com.clinica.estetica.service;

import com.clinica.estetica.model.Usuario;
import com.clinica.estetica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar ou atualizar usuário
    public Usuario criarUsuario(Usuario usuario) {
        usuario.setSenha(usuario.getSenha());  
        return usuarioRepository.save(usuario);
    }

    // Listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário por ID
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Atualizar usuário
    public Optional<Usuario> atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        if (usuarioRepository.existsById(id)) {
            //usuarioAtualizado.setId(id);
            return Optional.of(usuarioRepository.save(usuarioAtualizado));
        }
        return Optional.empty();
    }

    // Deletar usuário
    public boolean deletarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
