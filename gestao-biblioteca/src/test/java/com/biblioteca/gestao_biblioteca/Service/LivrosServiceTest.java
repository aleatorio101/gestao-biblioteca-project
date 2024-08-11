package com.biblioteca.gestao_biblioteca.Service;

import com.biblioteca.gestao_biblioteca.Repository.LivrosRepository;
import com.biblioteca.gestao_biblioteca.model.Livros;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LivrosServiceTest {

    @Mock
    private LivrosRepository livrosRepository;

    @Mock
    private GoogleBooksService googleBooksService;

    @InjectMocks
    private LivrosService livrosService;

    private Livros livro;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        livro = new Livros();
        livro.setId(1L);
        livro.setTitulo("Livro Teste");
        livro.setIsbn("1234567890123");
        livro.setAutor("Autor Teste");
        livro.setCategoria("Categoria Teste");
        livro.setDataPublicacao(null);
    }

    @Test
    public void testCriarLivro() {
        when(livrosRepository.save(any(Livros.class))).thenReturn(livro);

        Livros resultado = livrosService.criarLivro(livro);

        assertNotNull(resultado);
        assertEquals("Livro Teste", resultado.getTitulo());
        verify(livrosRepository).save(livro);
    }

    @Test
    public void testBuscarLivro() {
        when(livrosRepository.findById(1L)).thenReturn(Optional.of(livro));

        Optional<Livros> resultado = livrosService.buscarLivro(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Livro Teste", resultado.get().getTitulo());
        verify(livrosRepository).findById(1L);
    }

    @Test
    public void testAtualizarLivro() {
        when(livrosRepository.save(any(Livros.class))).thenReturn(livro);

        livro.setTitulo("Livro Atualizado");
        Livros resultado = livrosService.atualizarLivro(livro);

        assertNotNull(resultado);
        assertEquals("Livro Atualizado", resultado.getTitulo());
        verify(livrosRepository).save(livro);
    }

    @Test
    public void testDeletarLivro() {
        doNothing().when(livrosRepository).deleteById(1L);

        livrosService.deletarLivro(1L);

        verify(livrosRepository).deleteById(1L);
    }

    @Test
    public void testBuscarOuCriarLivroPorTitulo_LivroExistente() {
        when(livrosRepository.findByTitulo("Livro Teste")).thenReturn(Optional.of(livro));

        Livros resultado = livrosService.buscarOuCriarLivroPorTitulo("Livro Teste");

        assertNotNull(resultado);
        assertEquals("Livro Teste", resultado.getTitulo());
        verify(livrosRepository).findByTitulo("Livro Teste");
    }

    @Test
    public void testBuscarOuCriarLivroPorTitulo_LivroNaoExistente() {
        when(livrosRepository.findByTitulo("Livro Teste")).thenReturn(Optional.empty());
        when(googleBooksService.buscarLivroPorTitulo("Livro Teste")).thenReturn(Optional.of(livro));
        when(livrosRepository.save(any(Livros.class))).thenReturn(livro);

        Livros resultado = livrosService.buscarOuCriarLivroPorTitulo("Livro Teste");

        assertNotNull(resultado);
        assertEquals("Livro Teste", resultado.getTitulo());
        verify(livrosRepository).findByTitulo("Livro Teste");
        verify(googleBooksService).buscarLivroPorTitulo("Livro Teste");
        verify(livrosRepository).save(livro);
    }

    @Test
    public void testBuscarLivroPorIsbn_LivroExistente() {
        when(livrosRepository.findByIsbn("1234567890123")).thenReturn(Optional.of(livro));

        Livros resultado = livrosService.buscarLivroPorIsbn("1234567890123");

        assertNotNull(resultado);
        assertEquals("1234567890123", resultado.getIsbn());
        verify(livrosRepository).findByIsbn("1234567890123");
    }

    @Test
    public void testBuscarLivroPorIsbn_LivroNaoExistente() {
        when(livrosRepository.findByIsbn("1234567890123")).thenReturn(Optional.empty());
        when(googleBooksService.buscarLivroPorIsbn("1234567890123")).thenReturn(Optional.of(livro));
        when(livrosRepository.save(any(Livros.class))).thenReturn(livro);

        Livros resultado = livrosService.buscarLivroPorIsbn("1234567890123");

        assertNotNull(resultado);
        assertEquals("1234567890123", resultado.getIsbn());
        verify(livrosRepository).findByIsbn("1234567890123");
        verify(googleBooksService).buscarLivroPorIsbn("1234567890123");
        verify(livrosRepository).save(livro);
    }
}
