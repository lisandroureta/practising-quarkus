package org.agoncal.fascicle.quarkus.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.agoncal.fascicle.quarkus.book.modelo.Category;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AccesoCategoria implements PanacheRepositoryBase<Category, Long> {

  @Inject
  EntityManager em;

  @Transactional
  public Category persistCategoria(Category categoria) {
    persist(categoria);
    return categoria;
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Category> findAllCategorias() {
    return listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<Category> findCategoriaById(Long id) {
    return findByIdOptional(id);
  }

  public Category updateCategoria(Category categoria) {
    return em.merge(categoria);
  }

  public void deleteCategoria(Long id) {
    deleteById(id);
  }
}
