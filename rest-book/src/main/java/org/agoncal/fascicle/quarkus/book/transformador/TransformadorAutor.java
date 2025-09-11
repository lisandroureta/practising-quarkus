package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.Author;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleAutor;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleAutorCrear;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TransformadorAutor {

  // de entidad a DTO
  TransferibleAutor toTransferible(Author author);

  // lista de entidades a lista de DTOs
  List<TransferibleAutor> toTransferibleList(List<Author> authors);

  // de DTO crear a entidad
  Author toEntity(TransferibleAutorCrear dto);
}
