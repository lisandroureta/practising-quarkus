package org.agoncal.fascicle.quarkus.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.agoncal.fascicle.quarkus.book.modelo.Author;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AccesoAutor implements PanacheRepositoryBase<Author, Long> {

  @Inject
  EntityManager em;

  @Transactional
  public Author persistAuthor(Author author) {
    persist(author);
    return author;
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Author> findAllAuthors() {
    return listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<Author> findAuthorById(Long id) {
    return findByIdOptional(id);
  }

  @Transactional
  public Author updateAuthor(Author author) {
    return em.merge(author);
  }

  @Transactional
  public void deleteAuthor(Long id) {
    deleteById(id);
  }
}
