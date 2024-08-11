package com.biblioteca.gestao_biblioteca.Controller;

import com.biblioteca.gestao_biblioteca.Service.UsuariosService;
import com.biblioteca.gestao_biblioteca.model.Usuarios;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("usuarios")
public class UsuariosController {

    private UsuariosService usuariosService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> buscarUsuario(@PathVariable Long id) {
        Optional<Usuarios> usuario = usuariosService.buscarUsuario(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> atualizarUsuario(@PathVariable Long id, @RequestBody Usuarios usuarios) {
        usuarios.setId(id);
        Usuarios usuariosAtualizado = usuariosService.atualizarUsuario(usuarios);
        return ResponseEntity.ok(usuariosAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuariosService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}

