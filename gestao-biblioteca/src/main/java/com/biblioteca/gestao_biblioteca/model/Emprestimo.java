package com.biblioteca.gestao_biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Table (name = "emprestimo")
@Getter
@Setter
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuarios usuarios;

    @ManyToOne
    @JoinColumn(name = "livros_id")
    private Livros livros;

    private Date dataEmprestimo;
    private Date dataDevolucao;
    private String status;
}
