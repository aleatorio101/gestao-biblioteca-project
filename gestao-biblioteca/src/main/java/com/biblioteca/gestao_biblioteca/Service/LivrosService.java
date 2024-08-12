package com.biblioteca.gestao_biblioteca.Service;

import com.biblioteca.gestao_biblioteca.Repository.LivrosRepository;
import com.biblioteca.gestao_biblioteca.model.Livros;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LivrosService {

    private final LivrosRepository livrosRepository;
    private final GoogleBooksService googleBooksService;

    public Livros criarLivro(Livros livros) {
        return livrosRepository.save(livros);
    }

    public Optional<Livros> buscarLivro(Long id) {
        return livrosRepository.findById(id);
    }

    public Livros atualizarLivro(Livros livros) {
        return livrosRepository.save(livros);
    }

    public void deletarLivro(Long id) {
        livrosRepository.deleteById(id);
    }

    public Livros buscarOuCriarLivroPorTitulo(String titulo) {
        Optional<Livros> livroOptional = livrosRepository.findByTitulo(titulo);
        if (livroOptional.isPresent()) {
            return livroOptional.get();
        }

        Optional<Livros> livroGoogleOptional = googleBooksService.buscarLivroPorTitulo(titulo);
        if (livroGoogleOptional.isPresent()) {
            Livros livro = livroGoogleOptional.get();
            // Atualiza o livro se necessário
            return livrosRepository.save(livro);
        }

        throw new RuntimeException("Livro não encontrado.");
    }

    public Livros buscarLivroPorIsbn(String isbn) {
        Optional<Livros> livroOptional = livrosRepository.findByIsbn(isbn);
        if (livroOptional.isPresent()) {
            return livroOptional.get();
        }

        Optional<Livros> livroGoogleOptional = googleBooksService.buscarLivroPorIsbn(isbn);
        if (livroGoogleOptional.isPresent()) {
            Livros livro = livroGoogleOptional.get();
            return livrosRepository.save(livro);
        }

        throw new RuntimeException("Livro não encontrado.");
    }
    public List<Livros> listarTodosLivros() {
        return livrosRepository.findAll();
    }
}




