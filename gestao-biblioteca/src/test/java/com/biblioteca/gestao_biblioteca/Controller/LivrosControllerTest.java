package com.biblioteca.gestao_biblioteca.Controller;

import com.biblioteca.gestao_biblioteca.Service.LivrosService;
import com.biblioteca.gestao_biblioteca.model.Livros;
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

public class LivrosControllerTest {

    @InjectMocks
    private LivrosController livrosController;

    @Mock
    private LivrosService livrosService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarLivro() {
        Livros livro = new Livros();
        livro.setTitulo("1984");

        when(livrosService.criarLivro(livro)).thenReturn(livro);

        ResponseEntity<Livros> response = livrosController.criarLivro(livro);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(livro, response.getBody());
    }

    @Test
    public void testBuscarLivro_Success() {
        Long id = 1L;
        Livros livro = new Livros();
        livro.setId(id);
        livro.setTitulo("1984");

        when(livrosService.buscarLivro(id)).thenReturn(Optional.of(livro));

        ResponseEntity<Livros> response = livrosController.buscarLivro(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(livro, response.getBody());
    }

    @Test
    public void testBuscarLivro_NotFound() {
        Long id = 1L;

        when(livrosService.buscarLivro(id)).thenReturn(Optional.empty());

        ResponseEntity<Livros> response = livrosController.buscarLivro(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAtualizarLivro() {
        Long id = 1L;
        Livros livro = new Livros();
        livro.setId(id);
        livro.setTitulo("1984");

        when(livrosService.atualizarLivro(livro)).thenReturn(livro);

        ResponseEntity<Livros> response = livrosController.atualizarLivro(id, livro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(livro, response.getBody());
    }

    @Test
    public void testDeletarLivro() {
        Long id = 1L;

        ResponseEntity<Void> response = livrosController.deletarLivro(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testBuscarLivroPorTitulo_Success() {
        String titulo = "Dune";
        Livros livro = new Livros();
        livro.setTitulo(titulo);

        when(livrosService.buscarOuCriarLivroPorTitulo(titulo)).thenReturn(livro);

        ResponseEntity<Livros> response = livrosController.buscarLivroPorTitulo(titulo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(livro, response.getBody());
    }

    @Test
    public void testBuscarLivroPorTitulo_NotFound() {
        String titulo = "Dune";

        when(livrosService.buscarOuCriarLivroPorTitulo(titulo)).thenThrow(new RuntimeException("Livro não encontrado"));

        ResponseEntity<Livros> response = livrosController.buscarLivroPorTitulo(titulo);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testBuscarLivroPorIsbn_Success() {
        String isbn = "9780441013593";
        Livros livro = new Livros();
        livro.setIsbn(isbn);

        when(livrosService.buscarLivroPorIsbn(isbn)).thenReturn(livro);

        ResponseEntity<Livros> response = livrosController.buscarLivroPorIsbn(isbn);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(livro, response.getBody());
    }

    @Test
    public void testBuscarLivroPorIsbn_NotFound() {
        String isbn = "9780441013593";

        when(livrosService.buscarLivroPorIsbn(isbn)).thenThrow(new RuntimeException("Livro não encontrado"));

        ResponseEntity<Livros> response = livrosController.buscarLivroPorIsbn(isbn);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}