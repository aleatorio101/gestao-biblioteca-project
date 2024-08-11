package com.biblioteca.gestao_biblioteca.Service;

import com.biblioteca.gestao_biblioteca.Repository.LivrosRepository;
import com.biblioteca.gestao_biblioteca.model.Livros;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GoogleBooksService {


    private final LivrosRepository livrosRepository;

    private final String apiKey = "";

    private final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final Books service = new Books.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
            .setApplicationName("BibliotecaApp")
            .build();

    public Optional<Livros> buscarLivroPorIsbn(String isbn) {
        try {
            Books.Volumes.List request = service.volumes().list("isbn:" + isbn);
            request.setKey(apiKey);
            Volumes volumes = request.execute();

            if (volumes != null && volumes.getItems() != null && !volumes.getItems().isEmpty()) {
                Volume item = volumes.getItems().get(0);
                String tituloLivro = item.getVolumeInfo().getTitle();
                String autorLivro = item.getVolumeInfo().getAuthors() != null
                        ? item.getVolumeInfo().getAuthors().get(0)
                        : "N/A";
                String isbnLivro = item.getVolumeInfo().getIndustryIdentifiers() != null
                        ? item.getVolumeInfo().getIndustryIdentifiers().stream()
                        .filter(id -> "ISBN_13".equals(id.getType()))
                        .map(Volume.VolumeInfo.IndustryIdentifiers::getIdentifier)
                        .findFirst()
                        .orElse("N/A")
                        : "N/A";
                String publishedDateString = item.getVolumeInfo().getPublishedDate();
                Date dataPublicacaoLivro = parsePublishedDate(publishedDateString);
                String categoriaLivro = item.getVolumeInfo().getCategories() != null && !item.getVolumeInfo().getCategories().isEmpty()
                        ? item.getVolumeInfo().getCategories().get(0)
                        : "N/A";

                Livros livro = new Livros();
                livro.setTitulo(tituloLivro);
                livro.setAutor(autorLivro);
                livro.setIsbn(isbnLivro);
                livro.setDataPublicacao(dataPublicacaoLivro);
                livro.setCategoria(categoriaLivro);

                return Optional.of(livrosRepository.save(livro));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Livros> buscarLivroPorTitulo(String titulo) {
        try {
            Books.Volumes.List request = service.volumes().list("intitle:" + titulo);
            request.setKey(apiKey);
            Volumes volumes = request.execute();

            if (volumes != null && volumes.getItems() != null && !volumes.getItems().isEmpty()) {
                Volume item = volumes.getItems().get(0);
                String tituloLivro = item.getVolumeInfo().getTitle();
                String autorLivro = item.getVolumeInfo().getAuthors() != null
                        ? item.getVolumeInfo().getAuthors().get(0)
                        : "N/A";
                String isbnLivro = item.getVolumeInfo().getIndustryIdentifiers() != null
                        ? item.getVolumeInfo().getIndustryIdentifiers().stream()
                        .filter(id -> "ISBN_13".equals(id.getType()))
                        .map(Volume.VolumeInfo.IndustryIdentifiers::getIdentifier)
                        .findFirst()
                        .orElse("N/A")
                        : "N/A";
                String publishedDateString = item.getVolumeInfo().getPublishedDate();
                Date dataPublicacaoLivro = parsePublishedDate(publishedDateString);
                String categoriaLivro = item.getVolumeInfo().getCategories() != null && !item.getVolumeInfo().getCategories().isEmpty()
                        ? item.getVolumeInfo().getCategories().get(0)
                        : "N/A";

                Livros livro = new Livros();
                livro.setTitulo(tituloLivro);
                livro.setAutor(autorLivro);
                livro.setIsbn(isbnLivro);
                livro.setDataPublicacao(dataPublicacaoLivro);
                livro.setCategoria(categoriaLivro);

                return Optional.of(livro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Date parsePublishedDate(String publishedDate) {
        if (publishedDate == null || publishedDate.isEmpty()) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(publishedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}


