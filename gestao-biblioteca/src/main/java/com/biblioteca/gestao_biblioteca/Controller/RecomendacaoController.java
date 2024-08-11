package com.biblioteca.gestao_biblioteca.Controller;

import com.biblioteca.gestao_biblioteca.Service.RecomendacaoService;
import com.biblioteca.gestao_biblioteca.model.Livros;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/recomendacao")
public class RecomendacaoController {

    private RecomendacaoService recomendacaoService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Livros>> recomendarLivros(@PathVariable Long usuarioId) {
        List<Livros> livrosRecomendados = recomendacaoService.recomendarLivros(usuarioId);
        return ResponseEntity.ok(livrosRecomendados);
    }
}
