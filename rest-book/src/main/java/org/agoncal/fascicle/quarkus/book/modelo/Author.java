package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

@Schema(description = "Author representation")
@Entity
@Table(name = "authors")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String apellido;
  private String nacionalidad;

  // mappedBy = "authors" because Book has the @ManyToMany owning side
  @JsonbTransient // evitamos ciclos si por accidente se serializa la entidad
  @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
  private Set<Book> books = new HashSet<>();

  public Author() {}

  public Author(String nombre, String apellido, String nacionalidad) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.nacionalidad = nacionalidad;
  }

  // Getters / Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public String getApellido() { return apellido; }
  public void setApellido(String apellido) { this.apellido = apellido; }

  public String getNacionalidad() { return nacionalidad; }
  public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

  public Set<Book> getBooks() { return books; }
  public void setBooks(Set<Book> books) { this.books = books; }

  // helpers
  public void addBook(Book book) {
    this.books.add(book);
    book.getAuthors().add(this);
  }

  public void removeBook(Book book) {
    this.books.remove(book);
    book.getAuthors().remove(this);
  }

  @Override
  public String toString() {
    return "Author{" +
      "id=" + id +
      ", nombre='" + nombre + '\'' +
      ", apellido='" + apellido + '\'' +
      ", nacionalidad='" + nacionalidad + '\'' +
      '}';
  }
}
