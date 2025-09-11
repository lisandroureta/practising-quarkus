package org.agoncal.fascicle.quarkus.book.transferible;

public class TransferibleCategoria {
  private Long id;
  private String nombre;
  private Long categoriaPadreId; // opcional: para saber si tiene padre

  // ---------- Constructores ----------
  public TransferibleCategoria() {
  }

  public TransferibleCategoria(Long id, String nombre, Long categoriaPadreId) {
    this.id = id;
    this.nombre = nombre;
    this.categoriaPadreId = categoriaPadreId;
  }

  // ---------- Getters y Setters ----------
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
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
