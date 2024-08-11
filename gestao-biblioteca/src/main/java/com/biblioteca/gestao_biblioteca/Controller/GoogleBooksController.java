package com.biblioteca.gestao_biblioteca.Controller;

import com.biblioteca.gestao_biblioteca.Service.GoogleBooksService;
import com.biblioteca.gestao_biblioteca.model.Livros;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/google-books")
@AllArgsConstructor
public class GoogleBooksController {

    private final GoogleBooksService googleBooksService;

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarLivro(@RequestParam String titulo) {
        Optional<Livros> livro = googleBooksService.buscarLivroPorTitulo(titulo);
        return livro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

