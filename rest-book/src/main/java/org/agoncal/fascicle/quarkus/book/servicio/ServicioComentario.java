package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.agoncal.fascicle.quarkus.book.acceso.AccesoComentario;
import org.agoncal.fascicle.quarkus.book.acceso.AccesoLibro;
import org.agoncal.fascicle.quarkus.book.modelo.Book;
import org.agoncal.fascicle.quarkus.book.modelo.Comment;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleCategoria;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleComentario;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleComentarioCrear;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibro;
import org.agoncal.fascicle.quarkus.book.transformador.TransformadorComentario;

import java.util.List;
import java.util.Optional;

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
  public TransferibleComentario createComentario(TransferibleComentarioCrear dto) {
    // Convertir DTO de entrada a entidad
    Comment comment = transformador.toEntity(dto);

    // Buscar el libro por id (el bookId viene en el body del dto)
    Book book = accesoLibro.findBookById(dto.getBookId())
      .orElseThrow(() -> new NotFoundException("Libro no encontrado con id " + dto.getBookId()));

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
  public Optional<TransferibleComentario> findComentarioById(Long id) {
    return accesoComentario.findCommentById(id)
      .map(transformador::toTransferible);
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
