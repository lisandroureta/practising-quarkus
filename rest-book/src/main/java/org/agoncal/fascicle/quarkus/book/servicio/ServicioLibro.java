package org.agoncal.fascicle.quarkus.book.servicio;

//import ar.gob.ushuaia.exception.HttpNoContentException;
import org.agoncal.fascicle.quarkus.book.acceso.AccesoLibro;
import org.agoncal.fascicle.quarkus.book.client.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.client.NumberProxy;
import org.agoncal.fascicle.quarkus.book.modelo.Book;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class ServicioLibro {
  private static final Logger LOGGER = Logger.getLogger(ServicioLibro.class);

  @Inject
  EntityManager em;

  @Inject
  @RestClient
  NumberProxy numberProxy;
  @Inject
  AccesoLibro accesoLibro;

  @Fallback(fallbackMethod = "fallbackPersistBook")
  public Book persistBook(@Valid Book book) {
    IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
    book.isbn13 = isbnNumbers.getIsbn13();
    book.isbn10 = isbnNumbers.getIsbn10();

    accesoLibro.persistBook(book);
    return book;
  }

  private Book fallbackPersistBook(Book book) throws FileNotFoundException {
    LOGGER.warn("Falling back on persisting a book");
    String bookJson = JsonbBuilder.create().toJson(book);
    try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
      out.println(bookJson);
    }
    throw new IllegalStateException();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Book> findAllBooks() {
    /*If(listalibros != null || !listalibros.isemptuy()){

    }*/
    return accesoLibro.findAllBooks();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<Book> findBookById(Long id) {
    return accesoLibro.findByIdOptional(Math.toIntExact(id));
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Book findRandomBook() {
    return accesoLibro.findRandomBook();
  }

  public Book updateBook(@Valid Book book) {
    return accesoLibro.updateBook(book);
  }

  public void deleteBook(Long id) {
    accesoLibro.deleteById(Math.toIntExact(id));
  }
}
