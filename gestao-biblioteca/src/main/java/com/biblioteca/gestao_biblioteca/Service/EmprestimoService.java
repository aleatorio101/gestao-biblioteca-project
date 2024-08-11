package com.biblioteca.gestao_biblioteca.Service;

import com.biblioteca.gestao_biblioteca.Repository.EmprestimoRepository;
import com.biblioteca.gestao_biblioteca.model.Emprestimo;
import com.biblioteca.gestao_biblioteca.model.Livros;
import com.biblioteca.gestao_biblioteca.model.Usuarios;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivrosService livrosService;
    private final UsuariosService usuariosService;  // Novo: Injeção do serviço de usuários

    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public Optional<Emprestimo> buscarEmprestimo(Long id) {
        return emprestimoRepository.findById(id);
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public void deletarEmprestimo(Long id) {
        emprestimoRepository.deleteById(id);
    }

    public Emprestimo criarEmprestimo(Long usuarioId, String tituloLivro) {
        // Buscar ou criar o livro
        Livros livro = livrosService.buscarOuCriarLivroPorTitulo(tituloLivro);

        // Buscar o usuário pelo ID
        Usuarios usuario = usuariosService.buscarUsuario(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Criar o empréstimo
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuarios(usuario);  // Ajuste: Usando o usuário encontrado
        emprestimo.setLivros(livro);
        emprestimo.setDataEmprestimo(new Date()); // Ajuste a data conforme necessário
        emprestimo.setStatus("EMPRESTADO");

        return emprestimoRepository.save(emprestimo);
    }
}

