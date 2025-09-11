package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.Category;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleCategoria;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleCategoriaCrear;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TransformadorCategoria {

  // entidad -> DTO
  TransferibleCategoria toTransferible(Category category);

  // lista de entidades -> lista de DTOs
  List<TransferibleCategoria> toTransferibleList(List<Category> categories);

  // DTO crear -> entidad
  Category toEntity(TransferibleCategoriaCrear dto);
}
