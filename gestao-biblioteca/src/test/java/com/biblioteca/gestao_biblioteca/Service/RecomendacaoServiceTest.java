package com.biblioteca.gestao_biblioteca.Service;

import com.biblioteca.gestao_biblioteca.Repository.EmprestimoRepository;
import com.biblioteca.gestao_biblioteca.Repository.LivrosRepository;
import com.biblioteca.gestao_biblioteca.model.Emprestimo;
import com.biblioteca.gestao_biblioteca.model.Livros;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecomendacaoServiceTest {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private LivrosRepository livrosRepository;

    @InjectMocks
    private RecomendacaoService recomendacaoService;

    private Emprestimo emprestimo;
    private Livros livroEmprestado;
    private Livros livroDisponivel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        livroEmprestado = new Livros();
        livroEmprestado.setId(1L);
        livroEmprestado.setTitulo("Livro Emprestado");
        livroEmprestado.setCategoria("Categoria Teste");

        livroDisponivel = new Livros();
        livroDisponivel.setId(2L);
        livroDisponivel.setTitulo("Livro Disponível");
        livroDisponivel.setCategoria("Categoria Teste");

        emprestimo = new Emprestimo();
        emprestimo.setLivros(livroEmprestado);
    }

    @Test
    public void testRecomendarLivros() {
        // Mock dos empréstimos do usuário
        when(emprestimoRepository.findByUsuariosId(1L)).thenReturn(List.of(emprestimo));

        // Mock dos livros disponíveis
        when(livrosRepository.findAll()).thenReturn(List.of(livroEmprestado, livroDisponivel));

        // Executar o método
        List<Livros> livrosRecomendados = recomendacaoService.recomendarLivros(1L);

        // Verificar os resultados
        assertNotNull(livrosRecomendados);
        assertEquals(1, livrosRecomendados.size(), "Deve haver um livro recomendado.");
        assertEquals("Livro Disponível", livrosRecomendados.get(0).getTitulo(), "O livro recomendado está incorreto.");

        // Verificar as interações com os mocks
        verify(emprestimoRepository).findByUsuariosId(1L);
        verify(livrosRepository).findAll();
    }
}
