package org.agoncal.fascicle.quarkus.book.transferible;

public class TransferibleAutorCrear {
  private String nombre;
  private String apellido;
  private String nacionalidad;

  // ---------- Constructores ----------
  public TransferibleAutorCrear() {
  }

  public TransferibleAutorCrear(String nombre, String apellido, String nacionalidad) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.nacionalidad = nacionalidad;
  }

  // ---------- Getters y Setters ----------
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
