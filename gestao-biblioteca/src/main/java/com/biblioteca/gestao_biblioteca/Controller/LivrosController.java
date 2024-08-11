package com.biblioteca.gestao_biblioteca.Controller;

import com.biblioteca.gestao_biblioteca.Service.LivrosService;
import com.biblioteca.gestao_biblioteca.model.Livros;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/livros")
public class LivrosController {

    private final LivrosService livrosService;

    @PostMapping
    public ResponseEntity<Livros> criarLivro(@RequestBody Livros livros) {
        Livros novoLivros = livrosService.criarLivro(livros);
        return new ResponseEntity<>(novoLivros, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livros> buscarLivro(@PathVariable Long id) {
        Optional<Livros> livro = livrosService.buscarLivro(id);
        return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livros> atualizarLivro(@PathVariable Long id, @RequestBody Livros livros) {
        livros.setId(id);
        Livros livrosAtualizado = livrosService.atualizarLivro(livros);
        return ResponseEntity.ok(livrosAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        livrosService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }

    // Novo endpoint para buscar livro por título utilizando a API do Google Books
    @GetMapping("/buscaPorTitulo")
    public ResponseEntity<Livros> buscarLivroPorTitulo(@RequestParam String titulo) {
        try {
            Livros livro = livrosService.buscarOuCriarLivroPorTitulo(titulo);
            return ResponseEntity.ok(livro);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Novo endpoint para buscar livro por ISBN utilizando a API do Google Books
    @GetMapping("/buscaPorIsbn")
    public ResponseEntity<Livros> buscarLivroPorIsbn(@RequestParam String isbn) {
        try {
            Livros livro = livrosService.buscarLivroPorIsbn(isbn);
            return ResponseEntity.ok(livro);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

