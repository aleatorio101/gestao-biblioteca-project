package com.biblioteca.gestao_biblioteca.Service;

import com.biblioteca.gestao_biblioteca.Repository.EmprestimoRepository;
import com.biblioteca.gestao_biblioteca.Service.EmprestimoService;
import com.biblioteca.gestao_biblioteca.Service.LivrosService;
import com.biblioteca.gestao_biblioteca.Service.UsuariosService;
import com.biblioteca.gestao_biblioteca.model.Emprestimo;
import com.biblioteca.gestao_biblioteca.model.Livros;
import com.biblioteca.gestao_biblioteca.model.Usuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmprestimoServiceTest {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private LivrosService livrosService;

    @Mock
    private UsuariosService usuariosService;

    @InjectMocks
    private EmprestimoService emprestimoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarEmprestimo() {
        // Dados de entrada
        Long usuarioId = 1L;
        String tituloLivro = "Livro Teste";

        // Dados simulados
        Usuarios usuario = new Usuarios();
        usuario.setId(usuarioId);
        Livros livro = new Livros();
        livro.setTitulo(tituloLivro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuarios(usuario);
        emprestimo.setLivros(livro);
        emprestimo.setDataEmprestimo(new Date());
        emprestimo.setStatus("EMPRESTADO");

        // Comportamento esperado dos mocks
        when(livrosService.buscarOuCriarLivroPorTitulo(tituloLivro)).thenReturn(livro);
        when(usuariosService.buscarUsuario(usuarioId)).thenReturn(Optional.of(usuario));
        when(emprestimoRepository.save(any(Emprestimo.class))).thenReturn(emprestimo);

        // Chamada ao serviço
        Emprestimo resultado = emprestimoService.criarEmprestimo(usuarioId, tituloLivro);

        // Verificações
        assertNotNull(resultado);
        assertEquals(usuario, resultado.getUsuarios());
        assertEquals(livro, resultado.getLivros());
        assertEquals("EMPRESTADO", resultado.getStatus());
        verify(livrosService).buscarOuCriarLivroPorTitulo(tituloLivro);
        verify(usuariosService).buscarUsuario(usuarioId);
        verify(emprestimoRepository).save(any(Emprestimo.class));
    }

    @Test
    public void testBuscarEmprestimo() {
        Long emprestimoId = 1L;
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(emprestimoId);

        when(emprestimoRepository.findById(emprestimoId)).thenReturn(Optional.of(emprestimo));

        Optional<Emprestimo> resultado = emprestimoService.buscarEmprestimo(emprestimoId);

        assertTrue(resultado.isPresent());
        assertEquals(emprestimoId, resultado.get().getId());
        verify(emprestimoRepository).findById(emprestimoId);
    }

    @Test
    public void testAtualizarEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1L);

        when(emprestimoRepository.save(emprestimo)).thenReturn(emprestimo);

        Emprestimo resultado = emprestimoService.atualizarEmprestimo(emprestimo);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(emprestimoRepository).save(emprestimo);
    }

    @Test
    public void testDeletarEmprestimo() {
        Long emprestimoId = 1L;

        doNothing().when(emprestimoRepository).deleteById(emprestimoId);

        emprestimoService.deletarEmprestimo(emprestimoId);

        verify(emprestimoRepository).deleteById(emprestimoId);
    }
}