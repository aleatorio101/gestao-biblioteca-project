package com.biblioteca.gestao_biblioteca.Repository;

import com.biblioteca.gestao_biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByUsuariosId(Long usuariosId);
}
