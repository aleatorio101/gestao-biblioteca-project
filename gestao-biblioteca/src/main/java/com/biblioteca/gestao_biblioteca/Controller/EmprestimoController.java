package com.biblioteca.gestao_biblioteca.Controller;

import com.biblioteca.gestao_biblioteca.Service.EmprestimoService;
import com.biblioteca.gestao_biblioteca.model.Emprestimo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<Emprestimo> criarEmprestimo(@RequestParam Long usuarioId, @RequestParam String tituloLivro) {
        try {
            Emprestimo novoEmprestimo = emprestimoService.criarEmprestimo(usuarioId, tituloLivro);
            return new ResponseEntity<>(novoEmprestimo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarEmprestimo(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoService.buscarEmprestimo(id);
        return emprestimo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        Optional<Emprestimo> emprestimoExistente = emprestimoService.buscarEmprestimo(id);

        if (emprestimoExistente.isPresent()) {
            emprestimo.setId(id); // Certifique-se de que o ID está sendo definido corretamente
            Emprestimo emprestimoAtualizado = emprestimoService.atualizarEmprestimo(emprestimo);
            return ResponseEntity.ok(emprestimoAtualizado);
        } else {
            return ResponseEntity.notFound().build(); // Retorne 404 se o empréstimo não for encontrado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable Long id) {
        emprestimoService.deletarEmprestimo(id);
        return ResponseEntity.noContent().build();
    }
}