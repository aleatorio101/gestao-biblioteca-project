package com.biblioteca.gestao_biblioteca.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmprestimoTest {

    @Test
    public void testEmprestimo() {
        // Dados de teste
        Long id = 1L;
        Usuarios usuario = new Usuarios();
        usuario.setId(1L);

        Livros livro = new Livros();
        livro.setId(1L);

        Date dataEmprestimo = new Date();
        Date dataDevolucao = new Date();
        String status = "EMPRESTADO";

        // Criar a instância da entidade
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(id);
        emprestimo.setUsuarios(usuario);
        emprestimo.setLivros(livro);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimo.setStatus(status);

        // Verificações
        assertEquals(id, emprestimo.getId());
        assertEquals(usuario, emprestimo.getUsuarios());
        assertEquals(livro, emprestimo.getLivros());
        assertEquals(dataEmprestimo, emprestimo.getDataEmprestimo());
        assertEquals(dataDevolucao, emprestimo.getDataDevolucao());
        assertEquals(status, emprestimo.getStatus());
    }
}