package org.agoncal.fascicle.quarkus.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.agoncal.fascicle.quarkus.book.modelo.Comment;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AccesoComentario implements PanacheRepositoryBase<Comment, Long> {

  @Inject
  EntityManager em;

  @Transactional
  public Comment persistComment(Comment comment) {
    persist(comment);
    return comment;
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<Comment> findAllComments() {
    return listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<Comment> findCommentById(Long id) {
    return findByIdOptional(id);
  }

  @Transactional
  public Comment updateComment(Comment comment) {
    return em.merge(comment);
  }

  @Transactional
  public void deleteComment(Long id) {
    deleteById(id);
  }
}
