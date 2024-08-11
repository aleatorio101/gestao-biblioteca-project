package com.biblioteca.gestao_biblioteca.Repository;

import com.biblioteca.gestao_biblioteca.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivrosRepository extends JpaRepository<Livros, Long> {
    Optional<Livros> findByTitulo(String titulo);
    Optional<Livros> findByIsbn(String isbn);
}
