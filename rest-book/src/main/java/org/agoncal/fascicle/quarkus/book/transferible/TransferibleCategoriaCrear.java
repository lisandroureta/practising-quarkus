package org.agoncal.fascicle.quarkus.book.transferible;

public class TransferibleCategoriaCrear {
  private String nombre;
  private Long categoriaPadreId; // opcional al crear

  // ---------- Constructores ----------
  public TransferibleCategoriaCrear() {
  }

  public TransferibleCategoriaCrear(String nombre, Long categoriaPadreId) {
    this.nombre = nombre;
    this.categoriaPadreId = categoriaPadreId;
  }

  // ---------- Getters y Setters ----------
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public Long getCategoriaPadreId() {
    return categoriaPadreId;
  }
  public void setCategoriaPadreId(Long categoriaPadreId) {
    this.categoriaPadreId = categoriaPadreId;
  }
}
