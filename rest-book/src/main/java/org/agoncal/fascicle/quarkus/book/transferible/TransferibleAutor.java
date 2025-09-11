package org.agoncal.fascicle.quarkus.book.transferible;

public class TransferibleAutor {
  private Long id;
  private String nombre;
  private String apellido;
  private String nacionalidad;

  // ---------- Constructores ----------
  public TransferibleAutor() {
  }

  public TransferibleAutor(Long id, String nombre, String apellido, String nacionalidad) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.nacionalidad = nacionalidad;
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
  public String getApellido() {
    return apellido;
  }
  public void setApellido(String apellido) {
    this.apellido = apellido;
  }
  public String getNacionalidad() {
    return nacionalidad;
  }
  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }
}
