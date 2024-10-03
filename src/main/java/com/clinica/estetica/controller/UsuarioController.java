package com.clinica.estetica.controller;

import com.clinica.estetica.model.Usuario;
import com.clinica.estetica.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private ResponseEntity<?> gerarRespostaSucesso(Object data, String mensagem, HttpStatus status) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("codigo", status.value());
        resposta.put("mensagem", mensagem);
        resposta.put("data", data);
        return ResponseEntity.status(status).body(resposta);
    }

    private ResponseEntity<?> gerarRespostaErro(String mensagem, HttpStatus status) {
        Map<String, String> respostaErro = new HashMap<>();
        respostaErro.put("codigo", String.valueOf(status.value()));
        respostaErro.put("mensagem", mensagem);
        return ResponseEntity.status(status).body(respostaErro);
    }

    @ApiOperation(value = "Criar um novo usuário", response = Usuario.class)
    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@Validated @RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.criarUsuario(usuario);
            return gerarRespostaSucesso(novoUsuario, "Usuário criado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao criar o usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Listar todos os usuários", response = List.class)
    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            return gerarRespostaSucesso(usuarios, "Lista de usuários", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao listar os usuários: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Buscar usuário por ID", response = Usuario.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        try {
            Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);

            if (usuario.isPresent()) {
                return gerarRespostaSucesso(usuario.get(), "Usuário encontrado", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Usuário não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao buscar o usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Atualizar um usuário", response = Usuario.class)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @Validated @RequestBody Usuario usuario) {
        try {
            Optional<Usuario> usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);

            if (usuarioAtualizado.isPresent()) {
                return gerarRespostaSucesso(usuarioAtualizado.get(), "Usuário atualizado com sucesso", HttpStatus.OK);
            } else {
                return gerarRespostaErro("Usuário com ID " + id + " não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao atualizar o usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Deletar um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerUsuario(@PathVariable Long id) {
        try {
            usuarioService.deletarUsuario(id);
            return gerarRespostaSucesso(null, "Usuário removido com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return gerarRespostaErro("Erro ao remover o usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
