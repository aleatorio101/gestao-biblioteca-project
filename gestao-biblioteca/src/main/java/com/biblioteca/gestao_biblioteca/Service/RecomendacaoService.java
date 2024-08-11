package com.biblioteca.gestao_biblioteca.Service;

import com.biblioteca.gestao_biblioteca.Repository.EmprestimoRepository;
import com.biblioteca.gestao_biblioteca.Repository.LivrosRepository;
import com.biblioteca.gestao_biblioteca.model.Emprestimo;
import com.biblioteca.gestao_biblioteca.model.Livros;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RecomendacaoService {

        private final EmprestimoRepository emprestimoRepository;
        private final LivrosRepository livrosRepository;

        public List<Livros> recomendarLivros(Long usuarioId) {
            // Buscar todos os empréstimos do usuário
            List<Emprestimo> emprestimos = emprestimoRepository.findByUsuariosId(usuarioId);

            // Obter categorias dos livros emprestados
            List<String> categoriasEmprestadas = emprestimos.stream()
                    .map(emprestimo -> emprestimo.getLivros().getCategoria())
                    .distinct()
                    .toList();

            // Buscar todos os livros
            List<Livros> todosLivros = livrosRepository.findAll();

            // Filtrar livros que não foram emprestados pelo usuário e estão na mesma categoria

            return todosLivros.stream()
                    .filter(livro -> categoriasEmprestadas.contains(livro.getCategoria()) &&
                            emprestimos.stream().noneMatch(e -> e.getLivros().getId().equals(livro.getId())))
                    .collect(Collectors.toList());
        }
    }
