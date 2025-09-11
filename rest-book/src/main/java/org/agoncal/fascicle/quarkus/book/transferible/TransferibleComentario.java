package org.agoncal.fascicle.quarkus.book.transferible;

public class TransferibleComentario {
  private Long id;
  private String email;
  private String texto;
  private Integer puntuacion;

  // ---------- Constructores ----------
  public TransferibleComentario() {
  }

  public TransferibleComentario(Long id, String email, String texto, Integer puntuacion) {
    this.id = id;
    this.email = email;
    this.texto = texto;
    this.puntuacion = puntuacion;
  }

  // ---------- Getters y Setters ----------
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getTexto() {
    return texto;
  }
  public void setTexto(String texto) {
    this.texto = texto;
  }

  public Integer getPuntuacion() {
    return puntuacion;
  }
  public void setPuntuacion(Integer puntuacion) {
    this.puntuacion = puntuacion;
  }
}
