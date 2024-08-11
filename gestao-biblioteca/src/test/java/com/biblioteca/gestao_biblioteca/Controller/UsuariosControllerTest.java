package com.biblioteca.gestao_biblioteca.Controller;

import com.biblioteca.gestao_biblioteca.Service.UsuariosService;
import com.biblioteca.gestao_biblioteca.model.Usuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UsuariosControllerTest {

    @InjectMocks
    private UsuariosController usuariosController;

    @Mock
    private UsuariosService usuariosService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarUsuario_Success() {
        Long usuarioId = 1L;
        Usuarios usuario = new Usuarios();
        usuario.setId(usuarioId);

        when(usuariosService.buscarUsuario(usuarioId)).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuarios> response = usuariosController.buscarUsuario(usuarioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testBuscarUsuario_NotFound() {
        Long usuarioId = 1L;

        when(usuariosService.buscarUsuario(usuarioId)).thenReturn(Optional.empty());

        ResponseEntity<Usuarios> response = usuariosController.buscarUsuario(usuarioId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAtualizarUsuario_Success() {
        Long usuarioId = 1L;
        Usuarios usuario = new Usuarios();
        usuario.setId(usuarioId);
        usuario.setNome("João");

        when(usuariosService.atualizarUsuario(usuario)).thenReturn(usuario);

        ResponseEntity<Usuarios> response = usuariosController.atualizarUsuario(usuarioId, usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testDeletarUsuario_Success() {
        Long usuarioId = 1L;

        ResponseEntity<Void> response = usuariosController.deletarUsuario(usuarioId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}