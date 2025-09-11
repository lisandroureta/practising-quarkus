package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Comment representation")
@Entity
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String emailCreador;

  @Column(length = 2000)
  private String texto;

  @Min(1)
  @Max(10)
  private Integer puntuacion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;

  public Comment() {}

  public Comment(String emailCreador, String texto, Integer puntuacion) {
    this.emailCreador = emailCreador;
    this.texto = texto;
    this.puntuacion = puntuacion;
  }

  // Getters / Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getEmailCreador() { return emailCreador; }
  public void setEmailCreador(String emailCreador) { this.emailCreador = emailCreador; }

  public String getTexto() { return texto; }
  public void setTexto(String texto) { this.texto = texto; }

  public Integer getPuntuacion() { return puntuacion; }
  public void setPuntuacion(Integer puntuacion) { this.puntuacion = puntuacion; }

  public Book getBook() { return book; }
  public void setBook(Book book) { this.book = book; }
}
