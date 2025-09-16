package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import org.agoncal.fascicle.quarkus.book.acceso.AccesoAutor;
import org.agoncal.fascicle.quarkus.book.acceso.AccesoCategoria;
import org.agoncal.fascicle.quarkus.book.acceso.AccesoLibro;
import org.agoncal.fascicle.quarkus.book.client.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.client.NumberProxy;
import org.agoncal.fascicle.quarkus.book.modelo.Author;
import org.agoncal.fascicle.quarkus.book.modelo.Book;
import org.agoncal.fascicle.quarkus.book.modelo.Category;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibro;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibroCrear;
import org.agoncal.fascicle.quarkus.book.transformador.TransformadorLibro;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServicioLibro {

  @Inject
  AccesoLibro accesoLibro;

  @Inject
  AccesoAutor accesoAutor;

  @Inject
  AccesoCategoria accesoCategoria;

  @Inject
  TransformadorLibro transformador;

  @Inject
  @RestClient
  NumberProxy numberProxy;

  @Inject
  Logger auditor;


  // Persistir libro desde DTO de entrada
  public TransferibleLibro persistBook(@Valid TransferibleLibroCrear dto) {
    // Convertir DTO de entrada a entidad
    Book book = transformador.toEntity(dto);

    // Asociar autores
    if (dto.getAuthorIds() != null) { // si es null no se modifica nada
      List<Author> autores = new ArrayList<>();
      for (Long authorId : dto.getAuthorIds()) {
        Author autor = accesoAutor.findByIdOptional(authorId)
          .orElseThrow(() -> new NotFoundException("Autor no encontrado con id " + authorId));
        auditor.debug("Autor encontrado: "+autor);
        autores.add(autor);
      }
      book.setAuthors(autores);
    }

    // Asociar categoría
    if (dto.getCategoryId() != null) {
      Category categoria = accesoCategoria.findCategoriaById(dto.getCategoryId())
        .orElseThrow(() -> new NotFoundException("Categoría no encontrada con id " + dto.getCategoryId()));
      book.setCategory(categoria);
    }

    // Lógica de negocio: generar ISBN
    IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
    book.setIsbn13(isbnNumbers.getIsbn13());
    book.setIsbn10(isbnNumbers.getIsbn10());

    // Guardar en BD
    accesoLibro.persistBook(book);

    // Convertir entidad persistida a DTO de salida
    return transformador.toTransferible(book);
  }

  // Actualizar libro
  public TransferibleLibro updateBook(Long id, @Valid TransferibleLibroCrear dto) {
    // Buscar el libro existente en la BD
    Book existente = accesoLibro.findBookById(id)
      .orElseThrow(() -> new NotFoundException("Libro con id " + id + " no encontrado"));

    // Actualizar campos con los valores del DTO
    existente.setTitle(dto.getTitle());
    //existente.setAuthor(dto.getAuthor());
    existente.setYearOfPublication(dto.getYearOfPublication());
    existente.setNbOfPages(dto.getNbOfPages());
    existente.setRank(dto.getRank());
    existente.setPrice(dto.getPrice());
    //existente.setSmallImageUrl(dto.getSmallImageUrl());
    //existente.setMediumImageUrl(dto.getMediumImageUrl());
    existente.setDescription(dto.getDescription());

    // Asociar autores
    if (dto.getAuthorIds() != null) { // si es null no se modifica
      List<Author> autores = new ArrayList<>();
      for (Long authorId : dto.getAuthorIds()) {
        Author autor = accesoAutor.findByIdOptional(authorId)
          .orElseThrow(() -> new NotFoundException("Autor no encontrado con id " + authorId));
        autores.add(autor);
      }
      existente.setAuthors(autores); // si estaba vacío en dto → se vacía
    }

    // Asociar categoría
    if (dto.getCategoryId() != null) {
      Category categoria = accesoCategoria.findCategoriaById(dto.getCategoryId())
        .orElseThrow(() -> new NotFoundException("Categoría no encontrada con id " + dto.getCategoryId()));
      existente.setCategory(categoria);
    }

    // Guardar cambios en la BD
    Book actualizado = accesoLibro.updateBook(existente);

    // Convertir entidad a DTO de salida
    return transformador.toTransferible(actualizado);
  }

  // Buscar libro por ID
  public Optional<TransferibleLibro> findBookById(Long id) {
    Optional<Book> book = accesoLibro.findByIdOptional(Math.toIntExact(id));
    return book.map(transformador::toTransferible);
  }

  // Buscar todos los libros
  public List<TransferibleLibro> findAllBooks() {
    List<Book> books = accesoLibro.listAll();
    return transformador.toTransferibleList(books);
  }

  // Borrar libro
  public void deleteBook(Long id) {
    accesoLibro.deleteById(Math.toIntExact(id));
  }

  // Buscar libro random
  public TransferibleLibro findRandomBook() {
    Book book = accesoLibro.findRandomBook();
    return transformador.toTransferible(book);
  }
}
