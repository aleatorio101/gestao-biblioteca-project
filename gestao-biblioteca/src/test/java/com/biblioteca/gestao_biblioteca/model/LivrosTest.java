package com.biblioteca.gestao_biblioteca.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LivrosTest {

    @Test
    public void testLivros() {
        // Dados de teste
        Long id = 1L;
        String titulo = "O Hobbit";
        String autor = "J.R.R. Tolkien";
        String isbn = "978-0345339683";
        Date dataPublicacao = new Date();
        String categoria = "Ficção";

        // Criar a instância da entidade
        Livros livro = new Livros();
        livro.setId(id);
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setIsbn(isbn);
        livro.setDataPublicacao(dataPublicacao);
        livro.setCategoria(categoria);

        // Verificações
        assertEquals(id, livro.getId());
        assertEquals(titulo, livro.getTitulo());
        assertEquals(autor, livro.getAutor());
        assertEquals(isbn, livro.getIsbn());
        assertEquals(dataPublicacao, livro.getDataPublicacao());
        assertEquals(categoria, livro.getCategoria());
    }
}