package com.biblioteca.gestao_biblioteca.Repository;

import com.biblioteca.gestao_biblioteca.model.Livros;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class LivrosRepositoryTest {

    @Autowired
    private LivrosRepository livrosRepository;

    @BeforeEach
    public void setUp() {
        // Limpar o banco de dados antes de cada teste
        livrosRepository.deleteAll();
    }

    @Test
    public void testFindByTituloNotFound() {
        // Executar o método
        Optional<Livros> resultado = livrosRepository.findByTitulo("Título Inexistente");

        // Verificar o resultado
        assertFalse(resultado.isPresent(), "Nenhum livro deveria ser encontrado pelo título.");
    }

    @Test
    public void testFindByIsbnNotFound() {
        // Executar o método
        Optional<Livros> resultado = livrosRepository.findByIsbn("999-9-999-99999-9");

        // Verificar o resultado
        assertFalse(resultado.isPresent(), "Nenhum livro deveria ser encontrado pelo ISBN.");
    }
}