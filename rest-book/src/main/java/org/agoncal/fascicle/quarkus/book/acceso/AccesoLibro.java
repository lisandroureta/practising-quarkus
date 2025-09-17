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
import org.agoncal.fascicle.quarkus.book.modelo.Category;
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
  public Book persistBook(Book book) {
    persist(book);
    return book;
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Book> findAllBooks() {
    return listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<Book> findBookById(Long id) {
    return findByIdOptional(Math.toIntExact(id)); // ahora usa Long directamente
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

  @Transactional
  public Book updateBook(Book book) {
    return em.merge(book);
  }

  @Transactional
  public void deleteBook(Long id) {
    deleteById(Math.toIntExact(id)); // usa Long directamente
  }

  // Cuando necesitamos un metodo especifico tenemos que escribir una query en JPQL (el SQL de JPA, digamos),
  // no nos alcanza con los métodos que ya trae Panache (listAll(), findByIdOptional(), etc.).
  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Book> findBooksByAuthorId(Long authorId) {
    return em.createQuery(
        "SELECT b FROM Book b JOIN b.authors a WHERE a.id = :authorId", Book.class)
      .setParameter("authorId", authorId) // acá le pasamos el parámetro a la query
      .getResultList(); // esto devuelve la lista de libros encontrados
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Book> findBooksByCategories(List<Category> categories) {
    return em.createQuery("SELECT b FROM Book b WHERE b.category IN :categories", Book.class)
      .setParameter("categories", categories)
      .getResultList();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Book> findBooksByMinRating(double minScore) {
    return em.createQuery(
        "SELECT b FROM Book b " +
          "JOIN b.comments c " +
          "GROUP BY b " +
          "HAVING AVG(c.puntuacion) > :minScore", Book.class)
      .setParameter("minScore", minScore)
      .getResultList();
  }

}
