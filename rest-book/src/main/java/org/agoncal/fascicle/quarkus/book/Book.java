package org.agoncal.fascicle.quarkus.book;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Random;

@Schema(description = "Book representation")
@Entity
public class Book extends PanacheEntity {
  @Schema(required = true)
  public String title;
  @Column(name = "isbn_13")
  public String isbn13;
  @Column(name = "isbn_10")
  public String isbn10;
  public String author;
  @Column(name = "year_of_publication")
  public Integer yearOfPublication;
  @Column(name = "nb_of_pages")
  public Integer nbOfPages;
  public Integer rank;
  public BigDecimal price;
  @Column(name = "small_image_url")
  public URL smallImageUrl;
  @Column(name = "medium_image_url")
  public URL mediumImageUrl;
  @Column(length = 10000)
  public String description;

  public static Book findRandom() {
    long countBooks = Book.count(); // Cantidad total de libros
    int randomBook = new Random().nextInt((int) countBooks);
    return Book.findAll().page(randomBook, 1).firstResult();
  }
}
