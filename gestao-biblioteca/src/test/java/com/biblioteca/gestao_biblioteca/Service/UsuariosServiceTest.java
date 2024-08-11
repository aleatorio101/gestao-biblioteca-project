package com.biblioteca.gestao_biblioteca.Service;

import com.biblioteca.gestao_biblioteca.Repository.UsuariosRepository;
import com.biblioteca.gestao_biblioteca.model.Usuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuariosServiceTest {

    @Mock
    private UsuariosRepository usuariosRepository;

    @InjectMocks
    private UsuariosService usuariosService;

    private Usuarios usuario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuarios();
        usuario.setId(1L);
        usuario.setNome("João Silva");
        usuario.setEmail("joao.silva@example.com");
    }

    @Test
    public void testCriarUsuario() {
        // Mock do comportamento do save
        when(usuariosRepository.save(usuario)).thenReturn(usuario);

        // Executar o método
        Usuarios resultado = usuariosService.criarUsuario(usuario);

        // Verificar o resultado
        assertNotNull(resultado, "O usuário não deve ser nulo.");
        assertEquals(usuario.getId(), resultado.getId(), "O ID do usuário está incorreto.");
        assertEquals(usuario.getNome(), resultado.getNome(), "O nome do usuário está incorreto.");
        assertEquals(usuario.getEmail(), resultado.getEmail(), "O email do usuário está incorreto.");

        // Verificar interações
        verify(usuariosRepository).save(usuario);
    }

    @Test
    public void testBuscarUsuario() {
        // Mock do comportamento do findById
        when(usuariosRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Executar o método
        Optional<Usuarios> resultado = usuariosService.buscarUsuario(1L);

        // Verificar o resultado
        assertTrue(resultado.isPresent(), "O usuário deveria ser encontrado.");
        assertEquals(usuario, resultado.get(), "O usuário encontrado está incorreto.");

        // Verificar interações
        verify(usuariosRepository).findById(1L);
    }

    @Test
    public void testAtualizarUsuario() {
        // Mock do comportamento do save
        when(usuariosRepository.save(usuario)).thenReturn(usuario);

        // Executar o método
        Usuarios resultado = usuariosService.atualizarUsuario(usuario);

        // Verificar o resultado
        assertNotNull(resultado, "O usuário não deve ser nulo.");
        assertEquals(usuario.getId(), resultado.getId(), "O ID do usuário está incorreto.");
        assertEquals(usuario.getNome(), resultado.getNome(), "O nome do usuário está incorreto.");
        assertEquals(usuario.getEmail(), resultado.getEmail(), "O email do usuário está incorreto.");

        // Verificar interações
        verify(usuariosRepository).save(usuario);
    }

    @Test
    public void testDeletarUsuario() {
        // Executar o método
        usuariosService.deletarUsuario(1L);

        // Verificar interações
        verify(usuariosRepository).deleteById(1L);
    }
}