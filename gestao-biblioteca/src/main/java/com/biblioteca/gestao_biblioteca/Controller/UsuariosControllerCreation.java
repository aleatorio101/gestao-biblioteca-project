package com.biblioteca.gestao_biblioteca.Controller;

import com.biblioteca.gestao_biblioteca.Service.UsuariosService;
import com.biblioteca.gestao_biblioteca.model.Usuarios;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/criacao/usuarios")
@AllArgsConstructor
public class UsuariosControllerCreation {

    private final UsuariosService usuariosService;

    @PostMapping
    public ResponseEntity<String> criarUsuario(@RequestBody Usuarios usuarios) {
        usuariosService.criarUsuario(usuarios);
        return ResponseEntity.ok("Usuário criado com sucesso!");
    }
}

