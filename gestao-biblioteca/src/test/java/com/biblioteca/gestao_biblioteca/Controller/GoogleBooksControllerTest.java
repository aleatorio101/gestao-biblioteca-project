package com.biblioteca.gestao_biblioteca.Controller;

import com.biblioteca.gestao_biblioteca.Service.GoogleBooksService;
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

public class GoogleBooksControllerTest {

    @InjectMocks
    private GoogleBooksController googleBooksController;

    @Mock
    private GoogleBooksService googleBooksService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarLivro_Success() {
        Livros livro = new Livros();
        livro.setTitulo("Dune");

        when(googleBooksService.buscarLivroPorTitulo("Dune")).thenReturn(Optional.of(livro));

        ResponseEntity<?> response = googleBooksController.buscarLivro("Dune");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(livro, response.getBody());
    }

    @Test
    public void testBuscarLivro_NotFound() {
        when(googleBooksService.buscarLivroPorTitulo("Dune")).thenReturn(Optional.empty());

        ResponseEntity<?> response = googleBooksController.buscarLivro("Dune");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}