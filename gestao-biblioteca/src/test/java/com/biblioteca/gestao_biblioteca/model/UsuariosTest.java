package com.biblioteca.gestao_biblioteca.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsuariosTest {

    @Test
    public void testUsuarios() throws ParseException {
        // Dados de teste
        Long id = 1L;
        String nome = "João Pedro";
        String email = "joao.pedro@exemplo.com";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date dataCadastro = dateFormat.parse("2024-08-09T09:00:00.000Z");
        String telefone = "32221711";

        // Criar a instância da entidade
        Usuarios usuario = new Usuarios();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setDataCadastro(dataCadastro);
        usuario.setTelefone(telefone);

        // Verificações
        assertEquals(id, usuario.getId());
        assertEquals(nome, usuario.getNome());
        assertEquals(email, usuario.getEmail());
        assertEquals(dataCadastro, usuario.getDataCadastro());
        assertEquals(telefone, usuario.getTelefone());
    }

    @Test
    public void testJsonFormat() throws IOException, ParseException {
        // Dados de teste
        Long id = 1L;
        String nome = "João Pedro";
        String email = "joao.pedro@exemplo.com";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date dataCadastro = dateFormat.parse("2024-08-09T09:00:00.000Z");
        String telefone = "32221711";

        // Criar a instância da entidade
        Usuarios usuario = new Usuarios();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setDataCadastro(dataCadastro);
        usuario.setTelefone(telefone);

        // Testar serialização JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(usuario);
        assertNotNull(json);
        assertEquals("{\"id\":1,\"nome\":\"João Pedro\",\"email\":\"joao.pedro@exemplo.com\",\"dataCadastro\":\"2024-08-09T09:00:00.000Z\",\"telefone\":\"32221711\"}", json);
    }
}