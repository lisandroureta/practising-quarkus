package org.agoncal.fascicle.quarkus.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.client.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.client.NumberProxy;
import org.agoncal.fascicle.quarkus.book.modelo.Book;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RequestScoped
@Transactional(Transactional.TxType.REQUIRED) // por defecto los métodos de escritura tienen transacción
public class AccesoLibro implements PanacheRepositoryBase<Book,Integer> {

  @Inject
  EntityManager em;

  @Inject
  @RestClient
  NumberProxy numberProxy;

  @Transactional
  public Book persistBook(@Valid Book book) {
    persist(book);
    return book;
  }

  @Transactional(Transactional.TxType.SUPPORTS) // si hay una transacción activa la usa, si no hay, no abre ninguna (perfecto para consultas de solo lectura)
  public List<Book> findAllBooks() {
    return listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<Book> findBookById(Long id) {
    return findByIdOptional(Math.toIntExact(id));
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Book findRandomBook() {
    long countBooks = count();
    if (countBooks == 0){
      return null;
    }
    int randomBook = new Random().nextInt((int) countBooks);
    return findAll().page(randomBook, 1).firstResult();
  }

  public Book updateBook(@Valid Book book) {
    return em.merge(book);
  }

  public void deleteBook(Long id) {
    deleteById(Math.toIntExact(id));
  }

}
