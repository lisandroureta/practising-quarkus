package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Category representation")
@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  // Self-referencing: una categoría puede tener subcategorías
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @JsonbTransient
  private Category parent;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Category> subcategories = new ArrayList<>();

  // libros que pertenecen a esta categoría (no cargamos por defecto)
  @JsonbTransient
  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private List<Book> books = new ArrayList<>();

  public Category() {}

  public Category(String nombre) {
    this.nombre = nombre;
  }

  // Getters / Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public Category getParent() { return parent; }
  public void setParent(Category parent) { this.parent = parent; }

  public List<Category> getSubcategories() { return subcategories; }
  public void setSubcategories(List<Category> subcategories) { this.subcategories = subcategories; }

  public List<Book> getBooks() { return books; }
  public void setBooks(List<Book> books) { this.books = books; }

  // helpers
  public void addSubcategory(Category sub) {
    subcategories.add(sub);
    sub.setParent(this);
  }

  public void removeSubcategory(Category sub) {
    subcategories.remove(sub);
    sub.setParent(null);
  }
}
