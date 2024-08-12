package com.biblioteca.gestao_biblioteca.Service;

import com.biblioteca.gestao_biblioteca.Repository.LivrosRepository;
import com.biblioteca.gestao_biblioteca.model.Livros;
import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GoogleBooksServiceTest {

    @Mock
    private LivrosRepository livrosRepository;

    @Mock
    private Books service;

    @Mock
    private Books.Volumes volumes;

    @Mock
    private Books.Volumes.List request;

    @InjectMocks
    private GoogleBooksService googleBooksService;

    private final String apiKey = "";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarLivroPorIsbn_Success() throws IOException {
        String isbn = "1234567890123";

        // Configuração do mock para a resposta da API
        Volume volume = new Volume();
        Volume.VolumeInfo volumeInfo = new Volume.VolumeInfo();
        volumeInfo.setTitle("Livro teste");
        volumeInfo.setAuthors(List.of("Alexandro Garro Brito"));
        volumeInfo.setIndustryIdentifiers(List.of(
                new Volume.VolumeInfo.IndustryIdentifiers().setType("ISBN_13").setIdentifier(isbn)
        ));
        volumeInfo.setPublishedDate("2024-08-11");
        volumeInfo.setCategories(List.of("Categoria Teste"));
        volume.setVolumeInfo(volumeInfo);

        Volumes volumesResponse = new Volumes();
        volumesResponse.setItems(List.of(volume));

        // Configuração do mock
        when(service.volumes()).thenReturn(volumes);
        when(volumes.list("isbn:" + isbn)).thenReturn(request);
        when(request.setKey(apiKey)).thenReturn(request);
        when(request.execute()).thenReturn(volumesResponse);

        when(livrosRepository.save(any(Livros.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Executar o método
        Optional<Livros> resultado = googleBooksService.buscarLivroPorIsbn(isbn);

        // Verificar o resultado
        assertTrue(resultado.isPresent(), "O livro deveria ser encontrado pelo ISBN.");
        Livros livro = resultado.get();
        assertEquals("Livro teste", livro.getTitulo(), "Título do livro está incorreto.");
        assertEquals("Alexandro Garro Brito", livro.getAutor(), "Autor do livro está incorreto.");
        assertEquals(isbn, livro.getIsbn(), "ISBN do livro está incorreto.");

        // Validar a data
        Date expectedDate = parseDate("2024-08-11");
        assertEquals(expectedDate, livro.getDataPublicacao(), "Data de publicação está incorreta.");

        assertEquals("Categoria Teste", livro.getCategoria(), "Categoria do livro está incorreta.");
        verify(livrosRepository).save(any(Livros.class));
    }


    @Test
    public void testBuscarLivroPorTitulo() throws IOException {
        String titulo = "Livro teste";  // Ajuste o título aqui

        // Configuração do mock para a resposta da API
        Volume volume = new Volume();
        Volume.VolumeInfo volumeInfo = new Volume.VolumeInfo();
        volumeInfo.setTitle(titulo);
        volumeInfo.setAuthors(List.of("Alexandro Garro Brito"));  // Atualize o autor aqui
        volumeInfo.setIndustryIdentifiers(List.of(
                new Volume.VolumeInfo.IndustryIdentifiers().setType("ISBN_13").setIdentifier("1234567890123")
        ));
        volumeInfo.setPublishedDate("2024-08-11");
        volumeInfo.setCategories(List.of("Categoria Teste"));
        volume.setVolumeInfo(volumeInfo);

        Volumes volumesResponse = new Volumes();
        volumesResponse.setItems(List.of(volume));

        // Configuração do mock
        when(service.volumes()).thenReturn(volumes);
        when(volumes.list("intitle:" + titulo)).thenReturn(request);
        when(request.setKey(apiKey)).thenReturn(request);
        when(request.execute()).thenReturn(volumesResponse);

        // Executar o método
        Optional<Livros> resultado = googleBooksService.buscarLivroPorTitulo(titulo);

        // Verificar o resultado
        assertTrue(resultado.isPresent(), "O livro deveria ser encontrado pelo título.");
        Livros livro = resultado.get();
        assertEquals(titulo, livro.getTitulo(), "Título do livro está incorreto.");  // Ajuste o título esperado aqui
        assertEquals("Alexandro Garro Brito", livro.getAutor(), "Autor do livro está incorreto.");  // Atualize o autor esperado aqui
        assertEquals("N/A", livro.getIsbn(), "ISBN do livro está incorreto.");

        // Validar a data
        assertNull(livro.getDataPublicacao(), "Data de publicação não deveria ser null.");

        assertEquals("Technology & Engineering", livro.getCategoria(), "Categoria do livro está incorreta.");
        verify(livrosRepository).save(any(Livros.class));
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}



