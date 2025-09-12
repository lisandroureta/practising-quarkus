package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.agoncal.fascicle.quarkus.book.acceso.AccesoComentario;
import org.agoncal.fascicle.quarkus.book.acceso.AccesoLibro;
import org.agoncal.fascicle.quarkus.book.modelo.Book;
import org.agoncal.fascicle.quarkus.book.modelo.Comment;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleComentario;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleComentarioCrear;
import org.agoncal.fascicle.quarkus.book.transformador.TransformadorComentario;

import java.util.List;

@ApplicationScoped
public class ServicioComentario {

  @Inject
  AccesoComentario accesoComentario;

  @Inject
  AccesoLibro accesoLibro;

  @Inject
  TransformadorComentario transformador;

  // Crear comentario
  @Transactional
  public TransferibleComentario createComentario(Long bookId, TransferibleComentarioCrear dto) {
    // Convertir DTO de entrada a entidad
    Comment comment = transformador.toEntity(dto);

    // Buscar el libro por id (viene por URL)
    Book book = accesoLibro.findBookById(bookId)
      .orElseThrow(() -> new IllegalArgumentException("El libro con id " + bookId + " no existe"));

    // Asociar el libro al comentario
    comment.setBook(book);

    // Guardar el comentario en BD
    accesoComentario.persistComment(comment);

    // Convertir entidad persistida a DTO de salida
    return transformador.toTransferible(comment);
  }

  // Listar todos los comentarios
  @Transactional(Transactional.TxType.SUPPORTS)
  public List<TransferibleComentario> findAllComentarios() {
    return transformador.toTransferibleList(accesoComentario.findAllComments());
  }

  // Buscar comentario por id
  @Transactional(Transactional.TxType.SUPPORTS)
  public TransferibleComentario findComentarioById(Long id) {
    Comment comment = accesoComentario.findCommentById(id)
      .orElseThrow(() -> new IllegalArgumentException("Comentario no encontrado con id " + id));
    return transformador.toTransferible(comment);
  }

  // Actualizar comentario
  @Transactional
  public TransferibleComentario updateComentario(Long id, TransferibleComentarioCrear dto) {
    // Buscar el comentario existente en la BD
    Comment existente = accesoComentario.findCommentById(id)
      .orElseThrow(() -> new IllegalArgumentException("Comentario no encontrado con id " + id));

    // Actualizar campos con los valores del DTO
    existente.setEmailCreador(dto.getEmail());
    existente.setTexto(dto.getTexto());
    existente.setPuntuacion(dto.getPuntuacion());

    // Guardar cambios en la BD
    Comment actualizado = accesoComentario.updateComment(existente);

    // Convertir entidad a DTO de salida
    return transformador.toTransferible(actualizado);
  }

  // Eliminar comentario
  @Transactional
  public void deleteComentario(Long id) {
    accesoComentario.deleteComment(id);
  }
}
