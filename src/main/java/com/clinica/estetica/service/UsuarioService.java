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

    public Usuario criarUsuario(Usuario usuario) {
        usuario.setSenha(usuario.getSenha());  
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        if (usuarioRepository.existsById(id)) {
            usuarioAtualizado.setIdUsuario(id);
            return Optional.of(usuarioRepository.save(usuarioAtualizado));
        }
        return Optional.empty();
    }

    public boolean deletarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
