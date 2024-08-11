package com.biblioteca.gestao_biblioteca.Repository;

import com.biblioteca.gestao_biblioteca.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}
