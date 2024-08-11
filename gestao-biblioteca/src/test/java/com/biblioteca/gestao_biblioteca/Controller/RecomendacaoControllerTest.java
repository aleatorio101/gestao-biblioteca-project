package com.biblioteca.gestao_biblioteca.Controller;

import com.biblioteca.gestao_biblioteca.Service.RecomendacaoService;
import com.biblioteca.gestao_biblioteca.model.Livros;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RecomendacaoControllerTest {

    @InjectMocks
    private RecomendacaoController recomendacaoController;

    @Mock
    private RecomendacaoService recomendacaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRecomendarLivros_Success() {
        Long usuarioId = 1L;
        Livros livro1 = new Livros();
        livro1.setTitulo("Dune");
        Livros livro2 = new Livros();
        livro2.setTitulo("The Hitchhiker's Guide to the Galaxy");

        List<Livros> livrosRecomendados = Arrays.asList(livro1, livro2);

        when(recomendacaoService.recomendarLivros(usuarioId)).thenReturn(livrosRecomendados);

        ResponseEntity<List<Livros>> response = recomendacaoController.recomendarLivros(usuarioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(livrosRecomendados, response.getBody());
    }

    @Test
    public void testRecomendarLivros_EmptyList() {
        Long usuarioId = 1L;

        when(recomendacaoService.recomendarLivros(usuarioId)).thenReturn(Arrays.asList());

        ResponseEntity<List<Livros>> response = recomendacaoController.recomendarLivros(usuarioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(), response.getBody());
    }
}