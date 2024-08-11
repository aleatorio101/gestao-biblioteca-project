package com.biblioteca.gestao_biblioteca.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private Date dataCadastro;

    @Column
    private String telefone;

    public Usuarios(Long id, String nome, String email, Date dataCadastro, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCadastro = dataCadastro;
        this.telefone = telefone;
    }

    public Usuarios(Long usuarioId) {
    }
}