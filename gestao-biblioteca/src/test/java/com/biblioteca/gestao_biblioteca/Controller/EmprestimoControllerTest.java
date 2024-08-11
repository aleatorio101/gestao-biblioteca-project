package com.biblioteca.gestao_biblioteca.Controller;

import com.biblioteca.gestao_biblioteca.Service.EmprestimoService;
import com.biblioteca.gestao_biblioteca.model.Emprestimo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmprestimoControllerTest {

    @InjectMocks
    private EmprestimoController emprestimoController;

    @Mock
    private EmprestimoService emprestimoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarEmprestimo_Success() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1L);
        emprestimo.setDataEmprestimo(new Date());
        emprestimo.setStatus("EMPRESTADO");

        when(emprestimoService.criarEmprestimo(1L, "Dune")).thenReturn(emprestimo);

        ResponseEntity<Emprestimo> response = emprestimoController.criarEmprestimo(1L, "Dune");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(emprestimo, response.getBody());
    }

    @Test
    public void testCriarEmprestimo_NotFound() {
        when(emprestimoService.criarEmprestimo(1L, "Dune")).thenThrow(new RuntimeException("Livro ou usuário não encontrado"));

        ResponseEntity<Emprestimo> response = emprestimoController.criarEmprestimo(1L, "Dune");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testBuscarEmprestimo_Success() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1L);

        when(emprestimoService.buscarEmprestimo(1L)).thenReturn(Optional.of(emprestimo));

        ResponseEntity<Emprestimo> response = emprestimoController.buscarEmprestimo(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(emprestimo, response.getBody());
    }

    @Test
    public void testBuscarEmprestimo_NotFound() {
        when(emprestimoService.buscarEmprestimo(1L)).thenReturn(Optional.empty());

        ResponseEntity<Emprestimo> response = emprestimoController.buscarEmprestimo(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAtualizarEmprestimo_Success() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1L);
        emprestimo.setStatus("EMPRESTADO");

        when(emprestimoService.buscarEmprestimo(1L)).thenReturn(Optional.of(emprestimo));
        when(emprestimoService.atualizarEmprestimo(emprestimo)).thenReturn(emprestimo);

        ResponseEntity<Emprestimo> response = emprestimoController.atualizarEmprestimo(1L, emprestimo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(emprestimo, response.getBody());
    }

    @Test
    public void testAtualizarEmprestimo_NotFound() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1L);

        when(emprestimoService.buscarEmprestimo(1L)).thenReturn(Optional.empty());

        ResponseEntity<Emprestimo> response = emprestimoController.atualizarEmprestimo(1L, emprestimo);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeletarEmprestimo_Success() {
        ResponseEntity<Void> response = emprestimoController.deletarEmprestimo(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
