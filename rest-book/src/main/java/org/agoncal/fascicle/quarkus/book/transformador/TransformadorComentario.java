package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.Comment;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleComentario;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleComentarioCrear;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TransformadorComentario {

  // entidad -> DTO
  TransferibleComentario toTransferible(Comment comment);

  // lista de entidades -> lista de DTOs
  List<TransferibleComentario> toTransferibleList(List<Comment> comments);

  // DTO crear -> entidad
  Comment toEntity(TransferibleComentarioCrear dto);
}
