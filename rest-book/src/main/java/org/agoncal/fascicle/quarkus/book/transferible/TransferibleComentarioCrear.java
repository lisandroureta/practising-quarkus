package org.agoncal.fascicle.quarkus.book.transferible;

public class TransferibleComentarioCrear {
  private String email;
  private String texto;
  private Integer puntuacion;
  private Long bookId;

  // ---------- Constructores ----------
  public TransferibleComentarioCrear() {
  }

  public TransferibleComentarioCrear(String email, String texto, Integer puntuacion, Long bookId) {
    this.email = email;
    this.texto = texto;
    this.puntuacion = puntuacion;
    this.bookId = bookId;
  }

  // ---------- Getters y Setters ----------
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
  public Long getBookId() {
    return bookId;
  }
  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }
}
