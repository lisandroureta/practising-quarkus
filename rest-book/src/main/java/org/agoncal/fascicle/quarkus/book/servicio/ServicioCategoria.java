package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.agoncal.fascicle.quarkus.book.acceso.AccesoCategoria;
import org.agoncal.fascicle.quarkus.book.modelo.Category;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleAutor;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleCategoria;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleCategoriaCrear;
import org.agoncal.fascicle.quarkus.book.transformador.TransformadorCategoria;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServicioCategoria {

  @Inject
  AccesoCategoria accesoCategoria;

  @Inject
  TransformadorCategoria transformador;

  // Crear categoría
  @Transactional
  public TransferibleCategoria createCategoria(TransferibleCategoriaCrear dto) {
    // Convertir DTO de entrada a entidad
    Category categoria = new Category();
    categoria.setNombre(dto.getNombre());
    // si viene un parentId, buscamos la categoría padre y la asignamos
    if (dto.getCategoriaPadreId() != null) {
      Category parent = accesoCategoria.findCategoriaById(dto.getCategoriaPadreId())
        .orElseThrow(() -> new IllegalArgumentException("Categoría padre no encontrada con id " + dto.getCategoriaPadreId()));
      categoria.setParent(parent);
    }

    // Guardar en BD
    accesoCategoria.persistCategoria(categoria);

    // Convertir entidad persistida a DTO de salida
    return transformador.toTransferible(categoria);
  }

  // Listar todas las categorías
  @Transactional(Transactional.TxType.SUPPORTS)
  public List<TransferibleCategoria> findAllCategorias() {
    List<Category> categorias = accesoCategoria.findAllCategorias();
    return transformador.toTransferibleList(categorias);
  }

  // Buscar autor por id
  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<TransferibleCategoria> findCategoriaById(Long id) {
    return accesoCategoria.findCategoriaById(id)
      .map(transformador::toTransferible);
  }

  // Actualizar categoría
  @Transactional
  public TransferibleCategoria updateCategoria(Long id, TransferibleCategoriaCrear dto) {
    // Buscar el autor existente en la BD
    Category existente = accesoCategoria.findCategoriaById(id)
      .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con id " + id));

    // Actualizar campos con los valores del DTO
    existente.setNombre(dto.getNombre());
    // verificar que viene con categoria padre
    if (dto.getCategoriaPadreId() != null) {
      Category parent = accesoCategoria.findCategoriaById(dto.getCategoriaPadreId())
        .orElseThrow(() -> new IllegalArgumentException("Categoría padre no encontrada con id " + dto.getCategoriaPadreId()));
      // le pasamos como categoria padre un objeto categoria, no un Long id
      existente.setParent(parent);
    } else {
      existente.setParent(null); // si viene null, la dejamos sin padre
    }

    // Guardar cambios en la BD
    Category actualizado = accesoCategoria.updateCategoria(existente);

    // Convertir entidad a DTO de salida
    return transformador.toTransferible(actualizado);
  }

  // Eliminar categoría
  @Transactional
  public void deleteCategoria(Long id) {
    accesoCategoria.deleteCategoria(id);
  }
}
