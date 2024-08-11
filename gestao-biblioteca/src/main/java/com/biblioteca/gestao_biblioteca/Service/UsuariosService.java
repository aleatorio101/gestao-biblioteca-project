package com.biblioteca.gestao_biblioteca.Service;


import com.biblioteca.gestao_biblioteca.Repository.UsuariosRepository;
import com.biblioteca.gestao_biblioteca.model.Usuarios;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuariosService {
        private UsuariosRepository usuariosRepository;

        public Usuarios criarUsuario(Usuarios usuarios) {
            return usuariosRepository.save(usuarios);
        }

        public Optional<Usuarios> buscarUsuario(Long id) {
            return usuariosRepository.findById(id);
        }

        public Usuarios atualizarUsuario(Usuarios usuarios) {
            return usuariosRepository.save(usuarios);
        }

        public void deletarUsuario(Long id) {
            usuariosRepository.deleteById(id);
       }
}

