package org.agoncal.fascicle.quarkus.book.transferible;

public class TransferibleLibroCrear {

  private String title;
  private String author;

  // Constructor vacío
  public TransferibleLibroCrear() {
  }

  // Constructor con parámetros
  public TransferibleLibroCrear(String title, String author) {
    this.title = title;
    this.author = author;
  }

  // Getters y Setters
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
