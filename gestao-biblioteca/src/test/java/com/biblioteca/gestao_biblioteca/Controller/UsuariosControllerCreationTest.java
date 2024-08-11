package com.biblioteca.gestao_biblioteca.Controller;

import static org.junit.jupiter.api.Assertions.*;

import com.biblioteca.gestao_biblioteca.Service.UsuariosService;
import com.biblioteca.gestao_biblioteca.model.Usuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsuariosControllerCreationTest {

    @InjectMocks
    private UsuariosControllerCreation usuariosControllerCreation;

    @Mock
    private UsuariosService usuariosService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarUsuario_Success() {
        // Dados de teste
        Usuarios usuario = new Usuarios();
        usuario.setNome("Maria");
        usuario.setEmail("maria@example.com");

        // Mock do serviço
        when(usuariosService.criarUsuario(usuario)).thenReturn(usuario);

        // Chamada ao método
        ResponseEntity<String> response = usuariosControllerCreation.criarUsuario(usuario);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário criado com sucesso!", response.getBody());

        // Verifica se o serviço foi chamado
        verify(usuariosService).criarUsuario(usuario);
    }
}