package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.Book;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibro;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TransformadorLibro {

  // convierte una entidad en un DTO
  TransferibleLibro toTransferible(Book book);

  // convierte un DTO en una entidad
  Book toEntity(TransferibleLibro dto);

  // Listas de DTOs a listas de Entidades
  List<TransferibleLibro> toTransferibleList(List<Book> books);

  // Listas de Entidades a listas de DTOs
  List<Book> toEntityList(List<TransferibleLibro> transferibleLibroList);
}
