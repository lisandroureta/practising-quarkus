package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.Book;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibro;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibroCrear;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "jakarta-cdi",  uses = { TransformadorAutor.class }) // para que MapStruct sepa cómo convertir de Author a TransferibleAutor.
public interface TransformadorLibro {

  // Entidad → DTO de salida
  @Mapping(target = "categoryName", source = "category.nombre")
  @Mapping(target = "authors", source = "authors")
  TransferibleLibro toTransferible(Book book);

  // DTO de salida → Entidad (rara vez usado, pero lo dejamos)
  Book toEntity(TransferibleLibro dto);

  // DTO de entrada (crear) → Entidad
  Book toEntity(TransferibleLibroCrear dto);

  // Lista de entidades → lista de DTOs de salida
  List<TransferibleLibro> toTransferibleList(List<Book> books);

  List<Book> toEntityList(List<TransferibleLibro> transferibleLibroList);
}
