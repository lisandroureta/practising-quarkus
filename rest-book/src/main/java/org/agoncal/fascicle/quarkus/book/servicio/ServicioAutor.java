package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.agoncal.fascicle.quarkus.book.acceso.AccesoAutor;
import org.agoncal.fascicle.quarkus.book.modelo.Author;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleAutor;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleAutorCrear;
import org.agoncal.fascicle.quarkus.book.transformador.TransformadorAutor;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServicioAutor {

  @Inject
  AccesoAutor accesoAutor;

  @Inject
  TransformadorAutor transformador;

  // Crear autor
  @Transactional
  public TransferibleAutor persistAutor(TransferibleAutorCrear dto) {
    // Convertir DTO de entrada a entidad
    Author autor = transformador.toEntity(dto);

    // Guardar en BD
    accesoAutor.persistAuthor(autor);

    // Convertir entidad persistida a DTO de salida
    return transformador.toTransferible(autor);
  }

  // Listar todos los autores
  @Transactional(Transactional.TxType.SUPPORTS)
  public List<TransferibleAutor> findAllAutores() {
    List<Author> autores = accesoAutor.findAllAuthors();
    return transformador.toTransferibleList(autores);
  }

  // Buscar autor por id
  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<TransferibleAutor> findAutorById(Long id) {
    return accesoAutor.findAuthorById(id)
      .map(transformador::toTransferible);
  }

  // Actualizar autor
  @Transactional
  public TransferibleAutor updateAutor(Long id, TransferibleAutorCrear dto) {
    // Buscar el autor existente en la BD
    Author existente = accesoAutor.findAuthorById(id)
      .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado con id " + id));

    // Actualizar campos con los valores del DTO
    existente.setNombre(dto.getNombre());
    existente.setApellido(dto.getApellido());
    existente.setNacionalidad(dto.getNacionalidad());

    // Guardar cambios en la BD
    Author actualizado = accesoAutor.updateAuthor(existente);

    // Convertir entidad a DTO de salida
    return transformador.toTransferible(actualizado);
  }

  // Eliminar autor
  @Transactional
  public void deleteAutor(Long id) {
    accesoAutor.deleteAuthor(id);
  }
}
