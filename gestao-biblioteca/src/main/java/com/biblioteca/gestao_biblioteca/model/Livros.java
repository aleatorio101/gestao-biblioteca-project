package com.biblioteca.gestao_biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Data
@Entity
@Table(name = "livros")
@Getter
@Setter
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String autor;

    @Column
    private String isbn;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dataPublicacao;

    @Column
    private String categoria;
}


